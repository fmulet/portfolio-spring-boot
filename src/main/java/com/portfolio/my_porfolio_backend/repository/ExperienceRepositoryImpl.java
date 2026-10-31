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

import com.portfolio.my_porfolio_backend.model.Experience;

import lombok.RequiredArgsConstructor;

/**
 * ExperienceRepositoryImpl
 */
@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements IExperienceRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Experience> experienceRowMapper = (rs, numRow) -> {
        Experience experience = new Experience();
        experience.setId(rs.getLong("id"));
        experience.setJobTitle(rs.getString("job_title"));
        experience.setCompanyName(rs.getString("company_name"));
        experience.setDescription(rs.getString("description"));
        experience.setStartDate(rs.getObject("start_date", LocalDate.class));
        experience.setEndDate(rs.getObject("end_date", LocalDate.class));
        experience.setPersonalInfoId(rs.getLong("personal_info_id"));
        return experience;

    };

    @Override
    public Experience save(Experience experience) {
        if (experience.getId() == null) {
            String sql = "Insert Into experiences (job_title,company_name,description,start_date,end_date,personal_info_id) Values (?,?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, experience.getJobTitle());
                ps.setString(2, experience.getCompanyName());
                ps.setString(3, experience.getDescription());
                ps.setObject(4, experience.getStartDate());
                ps.setObject(5, experience.getEndDate());
                ps.setLong(6, experience.getPersonalInfoId());
                return ps;
            }, keyHolder);

            experience.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {

            String sql = "Update experiences Set job_title=?, company_name=?,description=?," +
                    "start_date=?,end_date=?,personal_info_id=? Where id = ?";
            jdbcTemplate.update(sql, experience.getJobTitle(), experience.getCompanyName(), experience.getDescription(),
                    experience.getStartDate(), experience.getEndDate(), experience.getPersonalInfoId(),
                    experience.getId());
        }
        return experience;
    }

    @Override
    public Optional<Experience> findById(Long id) {
        String sql = "Select * From experiences Where id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, experienceRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Experience> findAll() {
        String sql = "Select * From experiences";
        return jdbcTemplate.query(sql, experienceRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete From experiences Where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Experience> findByPersonalInfoId(Long personalInfoId) {
        String sql = "Select * From experiences Where personal_info_id=?";
        return jdbcTemplate.query(sql, experienceRowMapper,
                personalInfoId);
    }

}
