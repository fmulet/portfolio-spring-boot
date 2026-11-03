package com.portfolio.my_porfolio_backend.repository;

import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.User;

/**
 * IUserRepository
 */
public interface IUserRepository {

    Optional<User> findByUsername(String username);
}
