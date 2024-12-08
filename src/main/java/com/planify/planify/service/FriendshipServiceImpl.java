package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateFriendDTO;
import com.planify.planify.dto.UserMapper;
import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import com.planify.planify.repository.FriendshipRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository, UserService userService, UserMapper userMapper) {
        this.friendshipRepository = friendshipRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //method returns all users that are friends with a given userId
    @Override
    public List<BaseUserDTO> getFriendsByUserId(long userId) {
        List<User> friends = friendshipRepository.findFriendsByUserId(userId);

        List<BaseUserDTO> friendships = friends.stream().map(userMapper::toBaseUserDto).toList();

        return friendships;
    }

    //method creates a friendship between two users given both userIds
    @Override
    public Friendship createFriendship(CreateFriendDTO createFriendDTO) {
        if (friendshipRepository.existsByUserIds(createFriendDTO.userId(), createFriendDTO.userId())) {
            throw new IllegalStateException("Friendship already exists");
        }
        User user1 = userService.getUserById(createFriendDTO.userId());
        User user2 = userService.getUserById(createFriendDTO.friendId());

        Friendship friendship = new Friendship(user1, user2);

        return friendshipRepository.save(friendship);
    }


    @Override
    public Friendship deleteFriendship(long userId, long friendId) {
        // Database enforces userId < friendId
        if (userId > friendId) {
            long temp = userId;
            userId = friendId;
            friendId = temp;
        }
        long finalUserId = userId;
        long finalFriendId = friendId;
        Friendship friendship = friendshipRepository.findFriendshipByUserIds(userId, friendId)
                .orElseThrow(() -> new IllegalStateException("Friendship does not exist between user " + finalUserId + " and user " + finalFriendId));

        friendshipRepository.delete(friendship);
        return friendship;
    }


}
