package com.plannr.dto;

import com.plannr.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUserEntity(CreateUserDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        return user;
    }


    public BaseUserDTO toBaseUserDto(User user) {
        return new BaseUserDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail()
        );
    }


}
