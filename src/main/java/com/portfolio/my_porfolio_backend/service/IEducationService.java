package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Education;

/**
 * IEducationService
 */
public interface IEducationService {

    Education save(Education education);

    Optional<Education> findById(Long id);

    List<Education> findAll();

    void deleteById(Long id);

    List<Education> findByPersonalInfoId(Long personalInfoId);
}
