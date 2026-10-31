package com.portfolio.my_porfolio_backend.dto;

import com.portfolio.my_porfolio_backend.model.Project;

/**
 * ProjectMapper
 */
public class ProjectMapper {

    public static ProjectDto toDto(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDto projectDto = new ProjectDto();

        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setImageUrl(project.getImageUrl());
        projectDto.setProjectUrl(project.getProjectUrl());
        projectDto.setPersonalInfoId(project.getPersonalInfoId());
        return projectDto;
    }

    public static Project toEntity(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }
        Project project = new Project();

        project.setId(projectDto.getId());
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setImageUrl(projectDto.getImageUrl());
        project.setProjectUrl(projectDto.getProjectUrl());
        project.setPersonalInfoId(projectDto.getPersonalInfoId());
        return project;
    }
}
