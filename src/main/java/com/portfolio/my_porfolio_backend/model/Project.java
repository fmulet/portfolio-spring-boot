package com.portfolio.my_porfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private String projectUrl;

    private Long personalInfoId;
}
