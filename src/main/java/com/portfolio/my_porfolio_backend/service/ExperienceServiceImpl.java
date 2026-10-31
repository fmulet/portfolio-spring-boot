package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.Experience;
import com.portfolio.my_porfolio_backend.repository.IExperienceRepository;

import lombok.RequiredArgsConstructor;

/**
 * ExperienceServiceImpl
 */
@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements IExperienceService {

    private final IExperienceRepository experienceRepository;
    private final Validator validator;

    @Override
    @Transactional
    public Experience save(Experience experience) {

        BindingResult result = new BeanPropertyBindingResult(experience, "experience");
        validator.validate(experience, result);
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        if (experience.getEndDate().isAfter(experience.getEndDate())) {
            throw new IllegalArgumentException("End date is not after start date");
        }

        return experienceRepository.save(experience);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findByPersonalInfoId(personalInfoId);
    }

}
