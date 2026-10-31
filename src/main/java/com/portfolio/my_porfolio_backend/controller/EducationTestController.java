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

import com.portfolio.my_porfolio_backend.dto.EducationDto;
import com.portfolio.my_porfolio_backend.dto.EducationMapper;
import com.portfolio.my_porfolio_backend.model.Education;
import com.portfolio.my_porfolio_backend.service.IEducationService;

import lombok.RequiredArgsConstructor;

/**
 * PersonalInfoTestController
 */
@RestController
@RequestMapping("/api/test-education")
@RequiredArgsConstructor
public class EducationTestController {
    private final IEducationService educationService;

    @GetMapping("/all")
    public List<EducationDto> getAllEducation() {
        return educationService.findAll()
                .stream()
                .map(EducationMapper::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public Optional<EducationDto> getEducationById(@PathVariable Long id) {
        return educationService.findById(id)
                .map(EducationMapper::toDto);

    }

    @PostMapping
    public ResponseEntity<EducationDto> createEducation(@RequestBody EducationDto educationDto) {

        // 1. Recibe el DTO y lo convierts a Entidad para procesarlo/guardarlo
        Education educationEntity = EducationMapper.toEntity(educationDto);
        Education savedEducation = educationService.save(educationEntity);

        // 2. Convierte la Entidad guardada de nuevo a DTO para la respuesta
        EducationDto responseDto = EducationMapper.toDto(savedEducation);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PutMapping("/{id}")
    public EducationDto update(@PathVariable Long id, @RequestBody EducationDto educationDto) {
        Education experience = EducationMapper.toEntity(educationDto);
        experience.setId(id);
        Education updatedEducation = educationService.save(experience);
        return EducationMapper.toDto(updatedEducation);

    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id) {
        educationService.deleteById(id);
    }
}
