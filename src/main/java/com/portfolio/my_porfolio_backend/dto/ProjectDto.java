package com.portfolio.my_porfolio_backend.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProjectDto
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    @URL(message = "Image URL must be a valid URL")
    private String imageUrl;

    @URL(message = "Project URL must be a valid URL")
    private String projectUrl;

    @NotNull(message = "PersonalInfoId cannot be null")
    @Min(value = 1, message = "PersonalInfoId must be greater than or equal to 1")
    private Long personalInfoId;
}
