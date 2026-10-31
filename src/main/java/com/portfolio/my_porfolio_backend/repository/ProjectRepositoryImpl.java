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

import com.portfolio.my_porfolio_backend.model.Project;

import lombok.RequiredArgsConstructor;

/**
 * ProjectRepositoryImpl
 */
@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements IProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project info = new Project();
        info.setId(rs.getLong("id"));
        info.setTitle(rs.getString("title"));
        info.setDescription(rs.getString("description"));
        info.setImageUrl(rs.getString("image_url"));
        info.setProjectUrl(rs.getString("project_url"));
        info.setPersonalInfoId(rs.getLong("personal_info_id"));

        return info;
    };

    @Override
    public Project save(Project project) {
        if (project.getId() == null) {
            String sql = "Insert Into projects (title,description,image_url,"
                    +
                    "project_url,personal_info_id) Values(?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, project.getTitle());
                ps.setString(2, project.getDescription());
                ps.setString(3, project.getImageUrl());
                ps.setString(4, project.getProjectUrl());
                ps.setLong(5, project.getPersonalInfoId());
                return ps;

            }, keyHolder);

            project.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {
            String sql = "Update projects set title=?, description=?, image_url=?," +
                    "project_url=?, personal_info_id=?" +
                    " Where id = ?";
            jdbcTemplate.update(sql, project.getTitle(), project.getDescription(),
                    project.getImageUrl(), project.getProjectUrl(), project.getPersonalInfoId(), project.getId());
        }
        return project;
    }

    @Override
    public Optional<Project> findById(Long id) {
        String sql = "Select * From projects Where id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, projectRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Project> findAll() {
        String sql = "Select * From projects";
        return jdbcTemplate.query(sql, projectRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete From projects Where id=?";
        jdbcTemplate.update(sql, id);
    }

}
