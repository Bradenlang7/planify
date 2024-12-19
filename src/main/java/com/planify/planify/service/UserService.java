package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateUserDTO;
import com.planify.planify.dto.UpdateUserDTO;
import com.planify.planify.entity.User;

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

}
