package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Project;

/**
 * IProjectService
 */
public interface IProjectService {

    Project save(Project project);

    Optional<Project> findById(Long id);

    List<Project> findAll();

    void deleteById(Long id);
}
