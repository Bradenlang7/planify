package com.planify.planify.dto;

public record UpdateUserDTO(
        Long id,
        String username,
        String email,
        String password,
        String firstname,
        String lastname

) {
}
