package com.planify.planify.service;

import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import com.planify.planify.repository.FriendshipRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    public UserServiceImpl(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        //retrieve existing user from the db
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + user.getId()));
        //update existing user
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        //CHECK HERE FOR CONFLICTS?????
        return userRepository.save(existingUser);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public List<User> findFriends(Long userId) {
        return friendshipRepository.findFriendsByUserId(userId);
    }

    @Override
    public Friendship removeFriendship(Long userId, Long friendId) {
        Friendship friendship = friendshipRepository.findFriendshipByUserIds(userId, friendId)
                .orElseThrow(() -> new IllegalStateException("Friendship does not exist between user " + userId + " and user " + friendId));

        friendshipRepository.delete(friendship);
        return friendship;
    }

    @Override
    public Friendship addFriendship(Long userId, Long friendId) {
        if (friendshipRepository.existsByUserIds(userId, friendId)) {
            throw new IllegalStateException("Friendship already exists");
        }
        User user1 = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));
        User user2 = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + friendId));

        Friendship friendship = new Friendship();
        friendship.setUser1(user1);
        friendship.setUser2(user2);

        return friendshipRepository.save(friendship);

    }
}
