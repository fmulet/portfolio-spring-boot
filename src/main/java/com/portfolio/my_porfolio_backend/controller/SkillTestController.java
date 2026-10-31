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

import com.portfolio.my_porfolio_backend.dto.SkillDto;
import com.portfolio.my_porfolio_backend.dto.SkillMapper;
import com.portfolio.my_porfolio_backend.model.Skill;
import com.portfolio.my_porfolio_backend.service.ISkillService;

import lombok.RequiredArgsConstructor;

/**
 * PersonalInfoTestController
 */
@RestController
@RequestMapping("/api/test-skill")
@RequiredArgsConstructor
public class SkillTestController {
    private final ISkillService skillService;

    @GetMapping("/all")
    public List<SkillDto> getAllSkill() {
        return skillService.findAll()
                .stream()
                .map(SkillMapper::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        Optional<SkillDto> skill = skillService.findById(id).stream()
                .map(SkillMapper::toDto)
                .findFirst();
        if (skill.isPresent()) {
            return new ResponseEntity<>(skill.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        // 1. Recibe el DTO y lo convierts a Entidad para procesarlo/guardarlo
        Skill skillEntity = SkillMapper.toEntity(skillDto);
        Skill savedSkill = skillService.save(skillEntity);

        // 2. Convierte la Entidad guardada de nuevo a DTO para la respuesta
        SkillDto responseDto = SkillMapper.toDto(savedSkill);

        // 3. Retorna el DTO con el estatus 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PutMapping("/{id}")
    public SkillDto update(@PathVariable Long id, @RequestBody SkillDto skillDto) {
        Skill skill = SkillMapper.toEntity(skillDto);
        skill.setId(id);
        Skill updatedSkill = skillService.save(skill);
        return SkillMapper.toDto(updatedSkill);

    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id) {
        skillService.deleteById(id);
    }

    @GetMapping("/personal-info/{id}")
    public List<SkillDto> findSkillByPersonalInfoId(@PathVariable Long id) {
        return skillService.findByPersonalInfoId(id).stream()
                .map(SkillMapper::toDto)
                .toList();
    }
}
