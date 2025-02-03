package com.plannr.dto;

import jakarta.validation.constraints.NotNull;

public record CreateCommentDTO(
        @NotNull
        Long planId, //FK for Plan
        String content
) {
}
