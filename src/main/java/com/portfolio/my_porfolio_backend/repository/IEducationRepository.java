package com.portfolio.my_porfolio_backend.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Education;

/**
 * IEducationRepository
 */
public interface IEducationRepository {

    Education save(Education education);

    Optional<Education> findById(Long id);

    List<Education> findAll();

    void deleteById(Long id);

    List<Education> findByPersonalInfoId(Long personalInfoId);
}
