package com.jpbolanio.portfolio_backend.project.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequestDto(

    @NotBlank(message = "Project title cannot be blank")
    @Size(max = 200 ,message = "Project title cannot exceed 200 characters")
    String title,

    String slug,

    @NotBlank(message = "Short description cannot be blank")
    @Size(max = 500, message = "Short description cannot exceed 500 characters")
    String shortDescription,

    @NotBlank(message = "Full description cannot be blank")
    String fullDescription,

    @URL(message = "GitHub URL must be a valid URL")
    String githubUrl,

    @URL(message = "Live URL must be a valid URL")
    String liveUrl,

    boolean featured,

    Set<UUID> technologies,

    List<MediaRequestDto> mediafiles
) {}
