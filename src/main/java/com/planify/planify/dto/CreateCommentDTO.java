package com.planify.planify.dto;

import jakarta.validation.constraints.NotNull;

public record CreateCommentDTO(
        @NotNull
        Long planId, //FK for Plan
        String content
) {
}
