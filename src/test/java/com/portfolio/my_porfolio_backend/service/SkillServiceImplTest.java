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
import com.portfolio.my_porfolio_backend.model.Skill;
import com.portfolio.my_porfolio_backend.repository.ISkillRepository;

/**
 * SkillServiceImplTest
 */
@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    @Mock
    private ISkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfSkills() {
        // Arrange
        List<Skill> mockSkills = Arrays.asList(new Skill(), new Skill());
        when(skillRepository.findAll()).thenReturn(mockSkills);

        // Action
        List<Skill> result = skillService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsSkillWhenFound() {
        Long id = 1L;
        Skill skillMock = new Skill();
        when(skillRepository.findById(id)).thenReturn(Optional.of(skillMock));

        Optional<Skill> result = skillService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(skillMock, result.get());
        verify(skillRepository, times(1)).findById(id);
    }

    @Test
    void testSaveSkillThrowsExceptionWhenInvalid() {
        Skill invalidSkill = new Skill();
        doAnswer(invocationMock -> {
            BindingResult bindingResult = invocationMock.getArgument(1);
            bindingResult.rejectValue("name", "NotBlank", "Name is required");
            return null;
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));
        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill), "ValidationException expected");
        verify(skillRepository, never()).save(any(Skill.class));
    }

    @Test
    void testSaveSkillSavesValidSkill() {
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);

        doNothing().when(validator).validate(any(Skill.class), any(BindingResult.class));

        Skill savedSkill = skillService.save(validSkill);

        assertNotNull(savedSkill);
        verify(skillRepository, times(1)).save(validSkill);
    }

}
