package com.portfolio.my_porfolio_backend.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_porfolio_backend.model.Skill;

/**
 * ISkillRepository
 */
public interface ISkillRepository {

    Skill save(Skill skill);

    Optional<Skill> findById(Long id);

    List<Skill> findAll();

    void deleteById(Long id);

    List<Skill> findByPersonalInfoId(Long personalInfoId);
}
