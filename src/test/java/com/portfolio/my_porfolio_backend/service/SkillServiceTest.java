package com.portfolio.my_porfolio_backend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.Skill;
import com.portfolio.my_porfolio_backend.repository.ISkillRepository;

/**
 * SkillServiceTest
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillServiceTest {
    @Autowired
    private ISkillService skillService;

    @Autowired
    private ISkillRepository skillRepository;

    @Test
    void testSaveValidSkill() {
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        Skill savedSkill = skillService.save(validSkill);

        assertNotNull(savedSkill.getId(), "Saved object not assigned ID");

        assertNotNull(skillRepository.findById(savedSkill.getId()).orElse(null), "Saved object not exists in database");
    }

    @Test
    void testSaveInvalidSkill() {
        Skill invalidSkill = new Skill(null, "", 90, "fab fa-java", 1L);
        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill),
                "It should throw a ValidationException when the Skill name is empty.");

    }

}
