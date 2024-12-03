package com.planify.planify.dto;

public record BaseUserDTO(
        String firstname,
        String lastname,
        String username,
        String email
) {
}
