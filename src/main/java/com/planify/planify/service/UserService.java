package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateUserDTO;
import com.planify.planify.dto.UpdateUserDTO;
import com.planify.planify.entity.User;

public interface UserService {
    //method to persist user to the db
    User createUser(CreateUserDTO createUserDTO);

    //method to update a user in the db
    BaseUserDTO updateUser(UpdateUserDTO user);

    //method retrieves a user from the db based on unique email
    User getUserByEmail(String email);

    //method to retrieve a user from the db based on id
    User getUserById(long id);

    //method to delete a user based on id
    void deleteUser(long id);

}
