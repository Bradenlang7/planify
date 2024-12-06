package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateUserDTO;
import com.planify.planify.dto.UpdateUserDTO;
import com.planify.planify.dto.UserMapper;
import com.planify.planify.entity.User;
import com.planify.planify.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public User createUser(CreateUserDTO createUserDTO) {
        if (userRepository.existsByUsername(createUserDTO.username())) {
            throw new IllegalStateException("Username already exists");
        }
        User user = userMapper.toUserEntity(createUserDTO);

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public BaseUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        // Retrieve the existing user from the database
        User existingUser = userRepository.findById(updateUserDTO.id())
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + updateUserDTO.id()));

        // Update only the fields that are provided from the request body
        if (updateUserDTO.username() != null) {
            //If username is being updated - confirm that the name is not already in use.
            if (userRepository.existsByUsername(updateUserDTO.username())) {
                throw new IllegalStateException("Username already exists");
            }
            existingUser.setUsername(updateUserDTO.username());
        }
        if (updateUserDTO.email() != null) {
            existingUser.setEmail(updateUserDTO.email());
        }
        if (updateUserDTO.password() != null) {
            existingUser.setPassword(updateUserDTO.password()); // Hash the password
        }
        if (updateUserDTO.lastname() != null) {
            existingUser.setLastname(updateUserDTO.lastname());
        }
        if (updateUserDTO.firstname() != null) {
            existingUser.setFirstname(updateUserDTO.firstname());
        }

        // Save the updated user and return a DTO
        User updatedUser = userRepository.save(existingUser);
        return userMapper.toBaseUserDto(updatedUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found with email: " + email));
    }

    @Override
    public BaseUserDTO getBaseUserDTOById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + id));

        return userMapper.toBaseUserDto(user);
    }

    @Override
    public User getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + id));

        return user;
    }

    @Override
    public long deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + id));
        userRepository.delete(user);

        return user.getId();
    }

}
