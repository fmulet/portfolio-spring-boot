package com.portfolio.my_porfolio_backend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.Education;
import com.portfolio.my_porfolio_backend.repository.IEducationRepository;

/**
 * SkillServiceTest
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {
    @Autowired
    private IEducationService educationService;

    @Autowired
    private IEducationRepository educationRepository;

    @Test
    void testSaveValidEducation() {
        Education validEducation = new Education(null, "Third", "CIISA", LocalDate.now(), LocalDate.now(),
                "Software Engineer", 1L);
        Education savedEducation = educationService.save(validEducation);

        assertNotNull(savedEducation.getId(), "Saved object not assigned ID");

        assertNotNull(educationRepository.findById(savedEducation.getId()).orElse(null),
                "Saved object not exists in database");
    }

    @Test
    void testSaveInvalidEducation() {
        Education invalidEducation = new Education(null, "", "CIISA", LocalDate.now(), LocalDate.now(),
                "Software Engineer", 1L);
        assertThrows(ValidationException.class, () -> educationService.save(invalidEducation),
                "It should throw a ValidationException when the Skill degree is empty.");

    }

}
