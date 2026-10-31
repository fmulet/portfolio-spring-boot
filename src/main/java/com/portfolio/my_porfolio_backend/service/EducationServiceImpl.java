package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.Education;
import com.portfolio.my_porfolio_backend.repository.IEducationRepository;

import lombok.RequiredArgsConstructor;

/**
 * EducationServiceImpl
 */
@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducationService {

    private final IEducationRepository educationRepository;
    private final Validator validator;

    @Override
    @Transactional
    public Education save(Education education) {
        BindingResult result = new BeanPropertyBindingResult(education, "education");
        validator.validate(education, result);
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        if (education.getEndDate().isAfter(education.getEndDate())) {
            throw new IllegalArgumentException("End date is not after start date");
        }
        return educationRepository.save(education);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findByPersonalInfoId(personalInfoId);
    }

}
