package com.plannr.dto;

public record UpdateUserDTO(
        String username,
        String email,
        String password,
        String firstname,
        String lastname

) {
}
