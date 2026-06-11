package com.jpbolanio.portfolio_backend.technology.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTechnologyDto(
    @NotBlank(message = "Technology cannot be blank")
    String name,

    String iconUrl
) {}
