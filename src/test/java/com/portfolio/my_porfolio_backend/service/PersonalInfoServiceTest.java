package com.portfolio.my_porfolio_backend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.PersonalInfo;
import com.portfolio.my_porfolio_backend.repository.IPersonalInfoRepository;

/**
 * PersonalInfoServiceTest
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonalInfoServiceTest {
    @Autowired
    private IPersonalInfoService personalInfoService;

    @Autowired
    private IPersonalInfoRepository personalInfoRepository;

    @Test
    void testSaveValidPersonalInfo() {
        PersonalInfo validPersonalInfo = new PersonalInfo(null, "Luisa", "Layne", "Hola Mundo", "Lorem ipsum...",
                "https://www.example.com", 0, "luisa@example.com", "1234567", "https://www.example.com",
                "https://www.example.com");
        PersonalInfo savedPersonalInfo = personalInfoService.save(validPersonalInfo);

        assertNotNull(savedPersonalInfo.getId(), "Saved object not assigned ID");

        assertNotNull(personalInfoRepository.findById(savedPersonalInfo.getId()).orElse(null),
                "Saved object not exists in database");
    }

    @Test
    void testSaveInvalidPersonalInfo() {
        PersonalInfo invalidPersonalInfo = new PersonalInfo(null, "Luisa", "Layne", "Hola Mundo", "Lorem ipsum...",
                "https://www.example.com", -1, "luisa@example.com", "1234567", "https://www.example.com",
                "https://www.example.com");
        assertThrows(ValidationException.class, () -> personalInfoService.save(invalidPersonalInfo),
                "It should throw a ValidationException when the PersonalInfo years of experience is negative.");

    }

}
