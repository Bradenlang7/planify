package com.planify.planify.controller;

import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.dto.CreateFriendDTO;
import com.planify.planify.entity.Friendship;
import com.planify.planify.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/friendships")
public class FriendshipController {
    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    public ResponseEntity<Friendship> createFriendship(@RequestBody CreateFriendDTO createFriendDTO) {
        Friendship friendship = friendshipService.createFriendship(createFriendDTO);

        return ResponseEntity.ok(friendship);
    }

    @DeleteMapping("/users/{id1}/users/{id2}")
    public ResponseEntity<Friendship> deleteFriendship(@PathVariable Long id1, @PathVariable Long id2) {
        Friendship friendship = friendshipService.deleteFriendship(id1, id2);

        return ResponseEntity.ok(friendship);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<BaseUserDTO>> getFriendshipsByUserId(@PathVariable Long id {
        List<BaseUserDTO> usersFriends = friendshipService.getFriendsByUserId(id);

        return ResponseEntity.ok(usersFriends);
    }


}
