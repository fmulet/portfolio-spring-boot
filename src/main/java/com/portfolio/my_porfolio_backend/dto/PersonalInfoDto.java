package com.portfolio.my_porfolio_backend.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfoDto {
    private Long id;

    @NotBlank(message = "Field first_name not blank")
    private String firstName;

    @NotBlank(message = "Field last_name not blank")
    private String lastName;

    @NotBlank(message = "Field title not blank")
    private String title;

    @NotBlank(message = "Field profile_description not blank")
    private String profileDescription;

    @NotBlank(message = "Field profile_image_url not blank")
    private String profileImageUrl;

    @PositiveOrZero(message = "Field years_of_experience not positive")
    private Integer yearsOfExperience;

    @Email(message = "Field email not email")
    private String email;

    @NotBlank(message = "Field phone not blank")
    private String phone;

    @URL(message = "Field linkedin_url not blank")
    private String linkedinUrl;

    @URL(message = "Field github_url not blank")
    private String githubUrl;

}
