package com.portfolio.my_porfolio_backend.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Experience
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private Long id;

    private String jobTitle;

    private String companyName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Long personalInfoId;
}
