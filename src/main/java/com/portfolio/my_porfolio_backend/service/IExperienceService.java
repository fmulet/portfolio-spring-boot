package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Experience;

/**
 * IExperienceService
 */
public interface IExperienceService {

    Experience save(Experience education);

    Optional<Experience> findById(Long id);

    List<Experience> findAll();

    void deleteById(Long id);

    List<Experience> findByPersonalInfoId(Long personalInfoId);
}
