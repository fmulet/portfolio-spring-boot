package com.portfolio.my_porfolio_backend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.Experience;
import com.portfolio.my_porfolio_backend.repository.IExperienceRepository;

/**
 * SkillServiceTest
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExperienceServiceTest {
    @Autowired
    private IExperienceService experienceService;

    @Autowired
    private IExperienceRepository experienceRepository;

    @Test
    void testSaveValidExperience() {
        Experience validExperience = new Experience(null, "SoftwareEngineer", "CIISA", LocalDate.now(), LocalDate.now(),
                "Practice", 1L);
        Experience savedExperience = experienceService.save(validExperience);

        assertNotNull(savedExperience.getId(), "Saved object not assigned ID");

        assertNotNull(experienceRepository.findById(savedExperience.getId()).orElse(null),
                "Saved object not exists in database");
    }

    @Test
    void testSaveInvalidExperience() {
        Experience invalidExperience = new Experience(null, "", "CIISA", LocalDate.now(), LocalDate.now(),
                "Software Engineer", 1L);
        assertThrows(ValidationException.class, () -> experienceService.save(invalidExperience),
                "It should throw a ValidationException when the experience job title is empty.");

    }

}
