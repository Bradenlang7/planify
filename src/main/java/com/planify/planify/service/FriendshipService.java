package com.planify.planify.service;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateFriendDTO;
import com.planify.planify.entity.Friendship;
import com.planify.planify.enums.FriendshipStatusEnum;

import java.util.List;

public interface FriendshipService {
    List<BaseUserDTO> getFriendsByUserIdAndStatus(long id, FriendshipStatusEnum status);

    Friendship createFriendshipPending(CreateFriendDTO createFriendDTO);

    void deleteFriendship(long friendshipId);

    Friendship updateFriendShipStatusById(long friendshipId);

}
