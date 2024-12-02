package com.planify.planify.service;

import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import com.planify.planify.repository.FriendshipRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private FriendshipRepository friendshipRepository;
    private UserRepository userRepository;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    //method returns all users that are friends with a given userId
    @Override
    public List<User> getFriendsByUserId(long userId) {
        return friendshipRepository.findFriendsByUserId(userId);
    }

    //method creates a friendship between two users given both userIds
    @Override
    public Friendship createFriendship(long userId, long friendId) {
        if (friendshipRepository.existsByUserIds(userId, friendId)) {
            throw new IllegalStateException("Friendship already exists");
        }
        User user1 = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));
        User user2 = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + friendId));

        Friendship friendship = new Friendship(user1, user2);
        
        return friendshipRepository.save(friendship);
    }

    //method deletes a friendship between two users given both userIds
    @Override
    public Friendship deleteFriendship(long userId, long friendId) {
        Friendship friendship = friendshipRepository.findFriendshipByUserIds(userId, friendId)
                .orElseThrow(() -> new IllegalStateException("Friendship does not exist between user " + userId + " and user " + friendId));

        friendshipRepository.delete(friendship);
        return friendship;
    }


}
