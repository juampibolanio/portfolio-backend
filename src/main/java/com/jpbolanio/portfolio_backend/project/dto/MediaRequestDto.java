package com.jpbolanio.portfolio_backend.project.dto;

import jakarta.validation.constraints.NotBlank;

public record MediaRequestDto(
    @NotBlank String url,
    @NotBlank String mediaType,
    @NotBlank String cloudinaryPublicId,
    boolean main
) {}
