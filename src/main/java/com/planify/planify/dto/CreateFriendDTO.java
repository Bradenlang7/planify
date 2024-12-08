package com.planify.planify.dto;

import jakarta.validation.constraints.NotNull;

public record CreateFriendDTO(
        @NotNull
        Long userId,
        @NotNull
        Long friendId
) {
}
