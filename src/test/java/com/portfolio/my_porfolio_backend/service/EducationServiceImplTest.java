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
import com.portfolio.my_porfolio_backend.model.Education;
import com.portfolio.my_porfolio_backend.repository.IEducationRepository;

/**
 * EducationServiceImplTest
 */
@ExtendWith(MockitoExtension.class)
public class EducationServiceImplTest {

    @Mock
    private IEducationRepository educationRepository;

    @InjectMocks
    private EducationServiceImpl educationService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfEducations() {
        // Arrange
        List<Education> mockEducations = Arrays.asList(new Education(), new Education());
        when(educationRepository.findAll()).thenReturn(mockEducations);

        // Action
        List<Education> result = educationService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(educationRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsEducationWhenFound() {
        Long id = 1L;
        Education educationMock = new Education();
        when(educationRepository.findById(id)).thenReturn(Optional.of(educationMock));

        Optional<Education> result = educationService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(educationMock, result.get());
        verify(educationRepository, times(1)).findById(id);
    }

    @Test
    void testSaveEducationThrowsExceptionWhenInvalid() {
        Education invalidEducation = new Education();
        doAnswer(invocationMock -> {
            BindingResult bindingResult = invocationMock.getArgument(1);
            bindingResult.rejectValue("degree", "NotBlank", "Degree is required");
            return null;
        }).when(validator).validate(any(Education.class), any(BindingResult.class));
        assertThrows(ValidationException.class, () -> educationService.save(invalidEducation),
                "ValidationException expected");
        verify(educationRepository, never()).save(any(Education.class));
    }

    @Test
    void testSaveEducationSavesValidEducation() {
        Education validEducation = new Education(null, "Bachelor's Degree", "University of Example", LocalDate.now(),
                LocalDate.now(), "Example Description", 1L);
        when(educationRepository.save(any(Education.class))).thenReturn(validEducation);

        doNothing().when(validator).validate(any(Education.class), any(BindingResult.class));

        Education savedEducation = educationService.save(validEducation);

        assertNotNull(savedEducation);
        verify(educationRepository, times(1)).save(validEducation);
    }

}
