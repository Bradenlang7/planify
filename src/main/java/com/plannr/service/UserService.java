package com.plannr.service;

import com.plannr.dto.BaseUserDTO;
import com.plannr.dto.CreateUserDTO;
import com.plannr.dto.UpdateUserDTO;
import com.plannr.entity.User;

import java.util.List;

public interface UserService {
    //method to persist user to the db
    BaseUserDTO createUser(CreateUserDTO createUserDTO);

    //method to update a user in the db
    BaseUserDTO updateUser(long id, UpdateUserDTO user);

    //method retrieves a user from the db based on unique email
    User getUserByEmail(String email);

    User getUserByUsername(String username);

    User getUserById(long id);

    BaseUserDTO getBaseUserDTOById(long id);

    //method to delete a user based on id
    long deleteUser(long id);

    List<User> findAllUsersByIds(List<Long> ids);

}
