package com.plannr.dto;

//Data Transfer Object for User
public record CreateUserDTO(
        String firstname,
        String lastname,
        String username,
        String email,
        String password
) {
}



