package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateFriendDTO;
import com.planify.planify.entity.Friendship;

import java.util.List;

public interface FriendshipService {
    List<BaseUserDTO> getFriendsByUserId(long id);

    Friendship createFriendship(CreateFriendDTO createFriendDTO);

    Friendship deleteFriendship(long userId, long friendshipId);

}
