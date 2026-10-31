package com.portfolio.my_porfolio_backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_porfolio_backend.exception.ValidationException;
import com.portfolio.my_porfolio_backend.model.Experience;
import com.portfolio.my_porfolio_backend.repository.IExperienceRepository;

/**
 * ExperienceServiceImplTest
 */
@ExtendWith(MockitoExtension.class)
public class ExperienceServiceImplTest {

    @Mock
    private IExperienceRepository experienceRepository;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfExperiences() {
        // Arrange
        List<Experience> mockExperiences = Arrays.asList(new Experience(), new Experience());
        when(experienceRepository.findAll()).thenReturn(mockExperiences);

        // Action
        List<Experience> result = experienceService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(experienceRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsExperienceWhenFound() {
        Long id = 1L;
        Experience experienceMock = new Experience();
        when(experienceRepository.findById(id)).thenReturn(Optional.of(experienceMock));

        Optional<Experience> result = experienceService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(experienceMock, result.get());
        verify(experienceRepository, times(1)).findById(id);
    }

    @Test
    void testSaveExperienceThrowsExceptionWhenInvalid() {
        Experience invalidExperience = new Experience();
        doAnswer(invocationMock -> {
            BindingResult bindingResult = invocationMock.getArgument(1);
            bindingResult.rejectValue("jobTitle", "NotBlank", "Job title is required");
            return null;
        }).when(validator).validate(any(Experience.class), any(BindingResult.class));
        assertThrows(ValidationException.class, () -> experienceService.save(invalidExperience),
                "ValidationException expected");
        verify(experienceRepository, never()).save(any(Experience.class));
    }

    @Test
    void testSaveExperienceSavesValidExperience() {
        Experience validExperience = new Experience(null, "Example Job Title", "Example Company", LocalDate.now(),
                LocalDate.now(), "Example Description", 1L);
        when(experienceRepository.save(any(Experience.class))).thenReturn(validExperience);

        doNothing().when(validator).validate(any(Experience.class), any(BindingResult.class));

        Experience savedExperience = experienceService.save(validExperience);

        assertNotNull(savedExperience);
        verify(experienceRepository, times(1)).save(validExperience);
    }

}
