package com.jpbolanio.portfolio_backend.common.exceptions;

import java.util.List;

public record ErrorResponseDto(
    String message,
    List<String> details
) {}
