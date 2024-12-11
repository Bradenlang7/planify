package com.planify.planify.dto;

public record UpdateUserDTO(
        String username,
        String email,
        String password,
        String firstname,
        String lastname

) {
}
