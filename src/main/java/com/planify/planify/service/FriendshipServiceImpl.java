package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateFriendDTO;
import com.planify.planify.dto.UserMapper;
import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import com.planify.planify.enums.FriendshipStatusEnum;
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
    public List<BaseUserDTO> getFriendsByUserIdAndStatus(long userId, FriendshipStatusEnum status) {
        List<User> friends = friendshipRepository.findFriendsByUserIdAndStatus(userId, status);

        List<BaseUserDTO> friendships = friends.stream().map(userMapper::toBaseUserDto).toList();

        return friendships;
    }

    //method creates a friendship between two users given both userIds STAT
    @Override
    public Friendship createFriendshipPending(CreateFriendDTO createFriendDTO) {
        System.out.println(createFriendDTO.userId());
        if (friendshipRepository.existsByUserIds(createFriendDTO.userId(), createFriendDTO.friendId())) {
            throw new IllegalStateException("Friendship already exists");
        }
        System.out.println(friendshipRepository.existsByUserIds(createFriendDTO.userId(), createFriendDTO.userId()));
        User user1 = userService.getUserById(createFriendDTO.userId());
        User user2 = userService.getUserById(createFriendDTO.friendId());
        User sender = userService.getUserById(createFriendDTO.senderId());

        Friendship friendship = new Friendship(user1, user2, sender);

        return friendshipRepository.save(friendship);
    }

    @Override
    public void deleteFriendship(long friendshipId) {
        friendshipRepository.deleteById(friendshipId);

    }

    @Override
    public Friendship updateFriendShipStatusById(long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalStateException("Friendship does not exist"));

        friendship.setStatus(FriendshipStatusEnum.APPROVED);

        return friendshipRepository.save(friendship);
    }


}
