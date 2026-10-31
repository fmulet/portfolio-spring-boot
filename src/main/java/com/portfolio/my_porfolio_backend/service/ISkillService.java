package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Skill;

/**
 * ISkillService
 */
public interface ISkillService {

    Skill save(Skill skill);

    Optional<Skill> findById(Long id);

    List<Skill> findAll();

    void deleteById(Long id);

    List<Skill> findByPersonalInfoId(Long personalInfoId);
}
