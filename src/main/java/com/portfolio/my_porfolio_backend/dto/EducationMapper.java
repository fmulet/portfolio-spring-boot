package com.portfolio.my_porfolio_backend.dto;

import com.portfolio.my_porfolio_backend.model.Education;

/**
 * EducationMapper
 */
public class EducationMapper {

    public static EducationDto toDto(Education education) {
        if (education == null) {
            return null;
        }
        EducationDto educationDto = new EducationDto();

        educationDto.setId(education.getId());
        educationDto.setDescription(education.getDescription());
        educationDto.setDegree(education.getDegree());
        educationDto.setInstitution(education.getInstitution());
        educationDto.setStartDate(education.getStartDate());
        educationDto.setEndDate(education.getEndDate());
        educationDto.setPersonalInfoId(education.getPersonalInfoId());
        return educationDto;
    }

    public static Education toEntity(EducationDto educationDto) {
        if (educationDto == null) {
            return null;
        }
        Education education = new Education();

        education.setId(educationDto.getId());
        education.setDescription(educationDto.getDescription());
        education.setDegree(educationDto.getDegree());
        education.setInstitution(educationDto.getInstitution());
        education.setStartDate(educationDto.getStartDate());
        education.setEndDate(educationDto.getEndDate());
        education.setPersonalInfoId(educationDto.getPersonalInfoId());

        return education;
    }
}
