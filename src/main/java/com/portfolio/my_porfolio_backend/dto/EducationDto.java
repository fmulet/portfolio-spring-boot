package com.portfolio.my_porfolio_backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Education
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {

    private Long id;

    @NotBlank(message = "Field degree not blank")
    private String degree;

    @NotBlank(message = "Field institution not blank")
    private String institution;

    @NotNull(message = "Field start_date not null")
    @PastOrPresent(message = "Field start_date must be an instant")
    private LocalDate startDate;

    @PastOrPresent(message = "Field end_date must be an instant")
    private LocalDate endDate;

    @NotBlank(message = "Field description not blank")
    private String description;

    @NotNull(message = "Field personal_info_id not null")
    private Long personalInfoId;

}
