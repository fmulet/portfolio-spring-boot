package com.portfolio.my_porfolio_backend.dto;

import com.portfolio.my_porfolio_backend.model.PersonalInfo;

/**
 * PersonalInfoMapper
 */
public class PersonalInfoMapper {

    public static PersonalInfoDto toDto(PersonalInfo personalInfo) {
        if (personalInfo == null) {
            return null;
        }
        PersonalInfoDto personalInfoDto = new PersonalInfoDto();

        personalInfoDto.setId(personalInfo.getId());
        personalInfo.setFirstName(personalInfo.getFirstName());
        personalInfo.setLastName(personalInfo.getLastName());
        personalInfo.setTitle(personalInfo.getTitle());
        personalInfo.setProfileDescription(personalInfo.getProfileDescription());
        personalInfo.setProfileImageUrl(personalInfo.getProfileImageUrl());
        personalInfo.setYearsOfExperience(personalInfo.getYearsOfExperience());
        personalInfo.setPhone(personalInfo.getPhone());
        personalInfo.setEmail(personalInfo.getEmail());
        personalInfo.setGithubUrl(personalInfo.getGithubUrl());
        personalInfo.setLinkedinUrl(personalInfo.getLinkedinUrl());

        return personalInfoDto;
    }

    public static PersonalInfo toEntity(PersonalInfoDto personalInfoDto) {
        if (personalInfoDto == null) {
            return null;
        }
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setId(personalInfoDto.getId());
        personalInfo.setFirstName(personalInfoDto.getFirstName());
        personalInfo.setLastName(personalInfoDto.getLastName());
        personalInfo.setTitle(personalInfoDto.getTitle());
        personalInfo.setProfileDescription(personalInfoDto.getProfileDescription());
        personalInfo.setProfileImageUrl(personalInfoDto.getProfileImageUrl());
        personalInfo.setYearsOfExperience(personalInfoDto.getYearsOfExperience());
        personalInfo.setPhone(personalInfoDto.getPhone());
        personalInfo.setEmail(personalInfoDto.getEmail());
        personalInfo.setGithubUrl(personalInfoDto.getGithubUrl());
        personalInfo.setLinkedinUrl(personalInfoDto.getLinkedinUrl());

        return personalInfo;
    }
}
