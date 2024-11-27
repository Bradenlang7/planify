package com.planify.planify.service;

import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //method to persist user to the db
    User createUser(User user);

    //method to update a user in the db
    User updateUser(User user);

    //method retrieves a user from the db based on unique email
    Optional<User> getUserByEmail(String email);

    //method to retrieve a user from the db based on id
    Optional<User> getUserById(long id);

    //method to delete a user based on id
    void deleteUser(long id);

    //method retrieves a users friends(impl used with FriendShipRepository)
    List<User> findFriends(Long userId);

    //method removes a friendship
    Friendship removeFriendship(Long userId, Long friendshipId);

    //method adds a friendship
    Friendship addFriendship(Long userId, Long friendshipId);
}
