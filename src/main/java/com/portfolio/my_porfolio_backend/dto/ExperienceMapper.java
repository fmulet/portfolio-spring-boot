package com.portfolio.my_porfolio_backend.dto;

import com.portfolio.my_porfolio_backend.model.Experience;

/**
 * ExperienceMapper
 */
public class ExperienceMapper {

    public static ExperienceDto toDto(Experience experience) {
        if (experience == null) {
            return null;
        }
        ExperienceDto experienceDto = new ExperienceDto();

        experienceDto.setId(experience.getId());
        experienceDto.setDescription(experience.getDescription());
        experienceDto.setCompanyName(experience.getCompanyName());
        experienceDto.setJobTitle(experience.getJobTitle());
        experienceDto.setStartDate(experience.getStartDate());
        experienceDto.setEndDate(experience.getEndDate());
        experienceDto.setPersonalInfoId(experience.getPersonalInfoId());
        return experienceDto;
    }

    public static Experience toEntity(ExperienceDto experienceDto) {
        if (experienceDto == null) {
            return null;
        }
        Experience experience = new Experience();

        experience.setId(experienceDto.getId());
        experience.setDescription(experienceDto.getDescription());
        experience.setCompanyName(experienceDto.getCompanyName());
        experience.setJobTitle(experienceDto.getJobTitle());
        experience.setStartDate(experienceDto.getStartDate());
        experience.setEndDate(experienceDto.getEndDate());
        experience.setPersonalInfoId(experienceDto.getPersonalInfoId());

        return experience;
    }
}
