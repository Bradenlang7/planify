package com.planify.planify.dto;

public record BaseUserDTO(
        Long id,
        String firstname,
        String lastname,
        String username,
        String email
) {
}
