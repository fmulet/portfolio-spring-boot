package com.portfolio.my_porfolio_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {

    private Long id;

    @NotBlank(message = "Field name not blank")
    private String name;

    @NotNull(message = "Field level_percentage not null")
    @PositiveOrZero(message = "Field level_percentage not positive")
    @Max(value = 100, message = "Field level_percentage must be less than 100")
    private Integer levelPercentage;

    @NotBlank(message = "Field icon_class not blank")
    private String iconClass;

    @NotNull(message = "Field personal_info_id not null")
    private Long personalInfoId;

}
