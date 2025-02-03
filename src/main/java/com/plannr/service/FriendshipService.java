package com.plannr.service;

import com.plannr.dto.BaseUserDTO;
import com.plannr.dto.CreateFriendDTO;
import com.plannr.entity.Friendship;
import com.plannr.enums.FriendshipStatusEnum;

import java.util.List;

public interface FriendshipService {
    List<BaseUserDTO> getFriendsByUserIdAndStatus(long id, FriendshipStatusEnum status);

    Friendship createFriendshipPending(CreateFriendDTO createFriendDTO);

    void deleteFriendship(long friendshipId);

    Friendship updateFriendShipStatusById(long friendshipId);

}
