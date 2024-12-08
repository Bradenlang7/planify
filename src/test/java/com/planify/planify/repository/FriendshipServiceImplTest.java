package com.planify.planify.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.planify.planify.dto.BaseUserDTO;
import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import com.planify.planify.service.FriendshipServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserServiceIntegrationTest {

    @Autowired
    private FriendshipServiceImpl friendshipService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Test
    void testGetFriendsByUserId() throws JsonProcessingException {

        // Arrange: Create and save users
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setPassword("password");

        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setFirstname("Alice");
        user2.setLastname("Smith");
        user2.setPassword("password");

        User user3 = new User();
        user3.setUsername("user3");
        user3.setEmail("user3@example.com");
        user3.setFirstname("Bob");
        user3.setLastname("Brown");
        user3.setPassword("password");

        // Save users
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);

        // Create friendships
        Friendship friendship1 = new Friendship(user1, user2); // user1 ↔ user2
        Friendship friendship2 = new Friendship(user1, user3); // user1 ↔ user3

        friendshipRepository.save(friendship1);
        friendshipRepository.save(friendship2);

        // Act: Call the service method
        List<BaseUserDTO> friends = friendshipService.getFriendsByUserId(user1.getId());
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println(objectMapper.writeValueAsString(friends));
        // Assert: Verify the results
        assertNotNull(friends, "Friends list should not be null.");
        assertEquals(2, friends.size(), "User1 should have 2 friends.");

        // Validate the friend details
        assertTrue(friends.stream().anyMatch(dto -> dto.username().equals("user2")), "User2 should be a friend of User1");
        assertTrue(friends.stream().anyMatch(dto -> dto.username().equals("user3")), "User3 should be a friend of User1");
    }
}
