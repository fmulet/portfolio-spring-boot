package com.portfolio.my_porfolio_backend.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.portfolio.my_porfolio_backend.model.Skill;

import lombok.RequiredArgsConstructor;

/**
 * SkillRepositoryImpl
 */
@Repository
@RequiredArgsConstructor
public class SkillRepositoryImpl implements ISkillRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Skill> skillRowMapper = (rs, numRow) -> {
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setName(rs.getString("name"));
        skill.setLevelPercentage(rs.getInt("level_percentage"));
        skill.setIconClass(rs.getString("icon_class"));
        skill.setPersonalInfoId(rs.getLong("personal_info_id"));
        return skill;

    };

    @Override
    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            String sql = "Insert Into skills (name,level_percentage,icon_class,personal_info_id) Values (?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, skill.getName());
                ps.setInt(2, skill.getLevelPercentage());
                ps.setString(3, skill.getIconClass());
                ps.setLong(4, skill.getPersonalInfoId());
                return ps;
            }, keyHolder);

            skill.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {

            String sql = "Update skills Set name=?, level_percentage=?, icon_class=?," +
                    "personal_info_id=? Where id = ?";
            jdbcTemplate.update(sql, skill.getName(), skill.getLevelPercentage(),
                    skill.getIconClass(), skill.getPersonalInfoId(),
                    skill.getId());
        }
        return skill;

    }

    @Override
    public Optional<Skill> findById(Long id) {
        String sql = "Select * From skills Where id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, skillRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql = "Select * From skills";
        return jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete From skills Where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Skill> findByPersonalInfoId(Long personalInfoId) {
        String sql = "Select * From skills Where personal_info_id=?";
        return jdbcTemplate.query(sql, skillRowMapper,
                personalInfoId);

    }

}
