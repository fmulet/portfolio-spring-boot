package com.portfolio.my_porfolio_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.my_porfolio_backend.dto.ExperienceDto;
import com.portfolio.my_porfolio_backend.dto.ExperienceMapper;
import com.portfolio.my_porfolio_backend.model.Experience;
import com.portfolio.my_porfolio_backend.service.IExperienceService;

import lombok.RequiredArgsConstructor;

/**
 * PersonalInfoTestController
 */
@RestController
@RequestMapping("/api/test-experience")
@RequiredArgsConstructor
public class ExperienceTestController {
    private final IExperienceService experienceService;

    @GetMapping("/all")
    public List<ExperienceDto> getAllExperience() {
        return experienceService.findAll()
                .stream()
                .map(ExperienceMapper::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<ExperienceDto> getExperienceById(@PathVariable Long id) {
        Optional<ExperienceDto> experience = experienceService.findById(id).stream()
                .map(ExperienceMapper::toDto)
                .findFirst();
        if (experience.isPresent()) {
            return new ResponseEntity<>(experience.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping
    public ResponseEntity<ExperienceDto> createExperience(@RequestBody ExperienceDto experienceDto) {
        // 1. Recibe el DTO y lo convierts a Entidad para procesarlo/guardarlo
        Experience experienceEntity = ExperienceMapper.toEntity(experienceDto);
        Experience savedExperience = experienceService.save(experienceEntity);

        // 2. Convierte la Entidad guardada de nuevo a DTO para la respuesta
        ExperienceDto responseDto = ExperienceMapper.toDto(savedExperience);

        // 3. Retorna el DTO con el estatus 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PutMapping("/{id}")
    public ExperienceDto update(@PathVariable Long id, @RequestBody ExperienceDto experienceDto) {
        Experience experience = ExperienceMapper.toEntity(experienceDto);
        experience.setId(id);
        Experience updatedExperience = experienceService.save(experience);
        return ExperienceMapper.toDto(updatedExperience);

    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id) {
        experienceService.deleteById(id);
    }
}
