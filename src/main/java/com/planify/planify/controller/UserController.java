package com.planify.planify.controller;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateUserDTO;
import com.planify.planify.dto.UpdateUserDTO;
import com.planify.planify.dto.UserMapper;
import com.planify.planify.entity.User;
import com.planify.planify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userMapper = new UserMapper();
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        BaseUserDTO baseUserDTO = userService.updateUser(updateUserDTO);
        return ResponseEntity.ok(baseUserDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseUserDTO> getBaseUserDTOById(@PathVariable long id) {
        BaseUserDTO baseUserDTO = userService.getBaseUserDTOById(id);

        return ResponseEntity.ok(baseUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        long deletedId = userService.deleteUser(id);

        return ResponseEntity.ok("User with ID " + deletedId + " has been deleted");
    }
}
