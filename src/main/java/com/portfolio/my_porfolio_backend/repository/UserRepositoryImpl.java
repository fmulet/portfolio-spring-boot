package com.portfolio.my_porfolio_backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portfolio.my_porfolio_backend.model.User;

import lombok.RequiredArgsConstructor;

/**
 * UserRepositoryImpl
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    private User mapRowToUser(ResultSet rs, int numRow) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapRowToUser, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
