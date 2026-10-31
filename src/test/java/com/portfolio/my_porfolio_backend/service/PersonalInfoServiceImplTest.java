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
import com.portfolio.my_porfolio_backend.model.PersonalInfo;
import com.portfolio.my_porfolio_backend.repository.IPersonalInfoRepository;

/**
 * PersonalInfoServiceImplTest
 */
@ExtendWith(MockitoExtension.class)
public class PersonalInfoServiceImplTest {

    @Mock
    private IPersonalInfoRepository personalInfoRepository;

    @InjectMocks
    private PersonalInfoServiceImpl personalInfoService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfPersonalInfos() {
        // Arrange
        List<PersonalInfo> mockPersonalInfos = Arrays.asList(new PersonalInfo(), new PersonalInfo());
        when(personalInfoRepository.findAll()).thenReturn(mockPersonalInfos);

        // Action
        List<PersonalInfo> result = personalInfoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(personalInfoRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsPersonalInfoWhenFound() {
        Long id = 1L;
        PersonalInfo personalInfoMock = new PersonalInfo();
        when(personalInfoRepository.findById(id)).thenReturn(Optional.of(personalInfoMock));

        Optional<PersonalInfo> result = personalInfoService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(personalInfoMock, result.get());
        verify(personalInfoRepository, times(1)).findById(id);
    }

    @Test
    void testSavePersonalInfoThrowsExceptionWhenInvalid() {
        PersonalInfo invalidPersonalInfo = new PersonalInfo();
        doAnswer(invocationMock -> {
            BindingResult bindingResult = invocationMock.getArgument(1);
            bindingResult.rejectValue("lastName", "NotBlank", "Last name is required");
            return null;
        }).when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));
        assertThrows(ValidationException.class, () -> personalInfoService.save(invalidPersonalInfo),
                "ValidationException expected");
        verify(personalInfoRepository, never()).save(any(PersonalInfo.class));
    }

    @Test
    void testSavePersonalInfoSavesValidPersonalInfo() {
        PersonalInfo validPersonalInfo = new PersonalInfo(null, "John", "Doe", "Example title",
                "Example Profile Description", "http://www.example.com", 3, "john.doe@example.com", "1234567890",
                "http://linkedin.com", "http://github.com");

        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(validPersonalInfo);

        doNothing().when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));

        PersonalInfo savedPersonalInfo = personalInfoService.save(validPersonalInfo);

        assertNotNull(savedPersonalInfo);
        verify(personalInfoRepository, times(1)).save(validPersonalInfo);
    }

}
