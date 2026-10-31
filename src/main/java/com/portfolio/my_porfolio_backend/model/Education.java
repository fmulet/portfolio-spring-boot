package com.portfolio.my_porfolio_backend.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Education
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    private Long id;

    private String degree;

    private String institution;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Long personalInfoId;

}
