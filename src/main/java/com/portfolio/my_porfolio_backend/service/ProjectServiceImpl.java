package com.portfolio.my_porfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.Project;
import com.portfolio.my_porfolio_backend.repository.IProjectRepository;

import lombok.RequiredArgsConstructor;

/**
 * ProjectServiceImpl
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    private final IProjectRepository projectRepository;
    private final Validator validator;

    @Override
    @Transactional
    public Project save(Project project) {
        BindingResult result = new BeanPropertyBindingResult(project, "project");
        validator.validate(project, result);
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        return projectRepository.save(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

}
