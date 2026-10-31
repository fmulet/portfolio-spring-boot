package com.portfolio.my_porfolio_backend.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Experience;

/**
 * IExperienceRepository
 */
public interface IExperienceRepository {

    Experience save(Experience education);

    Optional<Experience> findById(Long id);

    List<Experience> findAll();

    void deleteById(Long id);

    List<Experience> findByPersonalInfoId(Long personalInfoId);
}
