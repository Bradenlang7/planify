package com.plannr.dto;

import jakarta.validation.constraints.NotNull;

public record CreateFriendDTO(
        @NotNull
        Long userId,
        @NotNull
        Long friendId,
        @NotNull
        Long senderId
) {
}
