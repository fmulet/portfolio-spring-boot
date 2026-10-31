package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.PersonalInfo;

/**
 * IPersonalInfoService
 */
public interface IPersonalInfoService {

    PersonalInfo save(PersonalInfo personalInfo);

    Optional<PersonalInfo> findById(Long id);

    List<PersonalInfo> findAll();

    void deleteById(Long id);
}
