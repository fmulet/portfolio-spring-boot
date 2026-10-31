package com.portfolio.my_porfolio_backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.my_porfolio_backend.dto.ProjectDto;
import com.portfolio.my_porfolio_backend.dto.ProjectMapper;
import com.portfolio.my_porfolio_backend.model.Project;
import com.portfolio.my_porfolio_backend.service.FileStorageService;
import com.portfolio.my_porfolio_backend.service.IProjectService;

import lombok.RequiredArgsConstructor;

/**
 * ProjectTestController
 */
@RestController
@RequestMapping("/api/test-project")
@RequiredArgsConstructor
public class ProjectTestController {
    private final IProjectService projectService;
    private final FileStorageService fileStorageService;

    @GetMapping("/all")
    public List<ProjectDto> getAllProject() {
        return projectService.findAll()
                .stream()
                .map(ProjectMapper::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public ProjectDto getProjectById(@PathVariable Long id) {
        Optional<ProjectDto> info = projectService.findById(id).stream()
                .map(ProjectMapper::toDto)
                .findFirst();
        if (info.isPresent()) {
            return info.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project information not found");
        }
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto,
            @RequestParam("file") MultipartFile file) {

        try {
            String imageUrl = fileStorageService.storeFile(file);
            projectDto.setImageUrl(imageUrl);
            // 1. Recibe el DTO y lo convierts a Entidad para procesarlo/guardarlo
            Project projectEntity = ProjectMapper.toEntity(projectDto);
            Project savedProject = projectService.save(projectEntity);

            // 2. Convierte la Entidad guardada de nuevo a DTO para la respuesta
            ProjectDto responseDto = ProjectMapper.toDto(savedProject);

            // 3. Retorna el DTO con el estatus 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }

    }

    @PutMapping("/{id}")
    public ProjectDto update(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        Project project = ProjectMapper.toEntity(projectDto);
        project.setId(id);
        Project updatedProject = projectService.save(project);
        return ProjectMapper.toDto(updatedProject);

    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id) {
        projectService.deleteById(id);
    }
}
