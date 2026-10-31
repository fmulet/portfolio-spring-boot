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
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.my_porfolio_backend.dto.PersonalInfoDto;
import com.portfolio.my_porfolio_backend.dto.PersonalInfoMapper;
import com.portfolio.my_porfolio_backend.model.PersonalInfo;
import com.portfolio.my_porfolio_backend.service.IPersonalInfoService;

import lombok.RequiredArgsConstructor;

/**
 * PersonalInfoTestController
 */
@RestController
@RequestMapping("/api/test-personal-info")
@RequiredArgsConstructor
public class PersonalInfoTestController {
    private final IPersonalInfoService personalInfoService;

    @GetMapping("/all")
    public List<PersonalInfoDto> getAllPersonalInfo() {
        return personalInfoService.findAll()
                .stream()
                .map(PersonalInfoMapper::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public PersonalInfoDto getPersonalInfoById(@PathVariable Long id) {
        Optional<PersonalInfoDto> info = personalInfoService.findById(id).stream()
                .map(PersonalInfoMapper::toDto)
                .findFirst();
        if (info.isPresent()) {
            return info.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal information not found");
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfoDto> createPersonalInfo(@RequestBody PersonalInfoDto personalInfoDto) {
        // 1. Recibe el DTO y lo convierts a Entidad para procesarlo/guardarlo
        PersonalInfo personalInfoEntity = PersonalInfoMapper.toEntity(personalInfoDto);
        PersonalInfo savedPersonalInfo = personalInfoService.save(personalInfoEntity);

        // 2. Convierte la Entidad guardada de nuevo a DTO para la respuesta
        PersonalInfoDto responseDto = PersonalInfoMapper.toDto(savedPersonalInfo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PutMapping("/{id}")
    public PersonalInfoDto update(@PathVariable Long id, @RequestBody PersonalInfoDto personalInfoDto) {
        PersonalInfo personalInfo = PersonalInfoMapper.toEntity(personalInfoDto);
        personalInfo.setId(id);
        PersonalInfo updatedPersonalInfo = personalInfoService.save(personalInfo);
        return PersonalInfoMapper.toDto(updatedPersonalInfo);

    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id) {
        personalInfoService.deleteById(id);
    }
}
