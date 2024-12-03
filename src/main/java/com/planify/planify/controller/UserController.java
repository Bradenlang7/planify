package com.planify.planify.controller;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateUserDTO;
import com.planify.planify.dto.UpdateUserDTO;
import com.planify.planify.dto.UserMapper;
import com.planify.planify.entity.User;
import com.planify.planify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userMapper = new UserMapper();
    }

    @PostMapping
    public User create(@RequestBody CreateUserDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseUserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserDTO updateUserDTO) {
        // Ensure the ID is correctly set in the DTO and create
        updateUserDTO = new UpdateUserDTO(id, updateUserDTO.username(), updateUserDTO.email(),
                updateUserDTO.password(), updateUserDTO.firstname(), updateUserDTO.lastname());

        BaseUserDTO baseUserDTO = userService.updateUser(updateUserDTO);
        return ResponseEntity.ok(baseUserDTO);
    }
}
