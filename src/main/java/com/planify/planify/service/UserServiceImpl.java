package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateUserDTO;
import com.planify.planify.dto.UpdateUserDTO;
import com.planify.planify.dto.UserMapper;
import com.planify.planify.entity.User;
import com.planify.planify.repository.FriendshipRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, FriendshipRepository friendshipRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
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
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User not found with email: " + email));
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found with id: " + id));
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + id));
        userRepository.delete(user);
    }

}
