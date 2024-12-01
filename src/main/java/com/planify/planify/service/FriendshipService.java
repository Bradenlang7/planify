package com.planify.planify.service;

import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;

import java.util.List;

public interface FriendshipService {
    List<User> getFriendsByUserId(long id);

    Friendship createFriendship(long userId, long friendshipId);

    Friendship deleteFriendship(long userId, long friendshipId);

}
