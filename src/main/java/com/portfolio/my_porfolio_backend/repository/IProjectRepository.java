package com.portfolio.my_porfolio_backend.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Project;

/**
 * IProjectRepository
 */
public interface IProjectRepository {

    Project save(Project project);

    Optional<Project> findById(Long id);

    List<Project> findAll();

    void deleteById(Long id);
}
