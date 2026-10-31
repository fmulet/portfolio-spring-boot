package com.portfolio.my_porfolio_backend.repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.portfolio.my_porfolio_backend.model.Education;

import lombok.RequiredArgsConstructor;

/**
 * EducationRepositoryImpl
 */
@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements IEducationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Education> educationRowMapper = (rs, numRow) -> {
        Education education = new Education();
        education.setId(rs.getLong("id"));
        education.setInstitution(rs.getString("institution"));
        education.setDegree(rs.getString("degree"));
        education.setDescription(rs.getString("description"));
        education.setStartDate(rs.getObject("start_date", LocalDate.class));
        education.setEndDate(rs.getObject("end_date", LocalDate.class));
        education.setPersonalInfoId(rs.getLong("personal_info_id"));
        return education;

    };

    @Override
    public Education save(Education education) {
        if (education.getId() == null) {
            String sql = "Insert Into educations (institution,description,degree, start_date,end_date,personal_info_id) Values (?,?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, education.getInstitution());
                ps.setString(2, education.getDescription());
                ps.setString(3, education.getDegree());
                ps.setObject(4, education.getStartDate());
                ps.setObject(5, education.getEndDate());
                ps.setLong(6, education.getPersonalInfoId());
                return ps;
            }, keyHolder);

            education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {

            String sql = "Update educations Set institution=?, description=?,degree=?," +
                    "start_date=?,end_date=?,personal_info_id=? Where id = ?";
            jdbcTemplate.update(sql, education.getInstitution(), education.getDescription(), education.getDegree(),
                    education.getStartDate(), education.getEndDate(), education.getPersonalInfoId(),
                    education.getId());
        }
        return education;

    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql = "Select * From educations Where id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "Select * From educations";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete From educations Where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        String sql = "Select * From educations Where personal_info_id=?";
        return jdbcTemplate.query(sql, educationRowMapper,
                personalInfoId);

    }

}
