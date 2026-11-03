package com.portfolio.my_porfolio_backend.dto;

import com.portfolio.my_porfolio_backend.model.Skill;

/**
 * SkillMapper
 */
public class SkillMapper {

    public static SkillDto toDto(Skill skill) {
        if (skill == null) {
            return null;
        }
        SkillDto skillDto = new SkillDto();

        skillDto.setId(skill.getId());
        skillDto.setName(skill.getName());
        skillDto.setIconClass(skill.getIconClass());
        skillDto.setLevelPercentage(skill.getLevelPercentage());
        skillDto.setPersonalInfoId(skill.getPersonalInfoId());
        return skillDto;
    }

    public static Skill toEntity(SkillDto skillDto) {
        if (skillDto == null) {
            return null;
        }
        Skill skill = new Skill();

        skill.setId(skillDto.getId());
        skill.setName(skillDto.getName());
        skill.setIconClass(skillDto.getIconClass());
        skill.setLevelPercentage(skillDto.getLevelPercentage());
        skill.setPersonalInfoId(skillDto.getPersonalInfoId());
        return skill;
    }
}
