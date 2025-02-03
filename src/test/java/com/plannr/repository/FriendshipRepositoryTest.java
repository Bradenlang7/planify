package com.plannr.repository;

import com.plannr.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FriendshipRepositoryTest {
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void FriendshipRepository_ExistsByUserIds_ReturnsCorrectResults() {
        // Arrange: Create and save users
        User alice = new User("firstName", "lastName", "Alice", "alice@example.com", "password123");
        User bob = new User("firstName", "lastname", "Bob", "bob@example.com", "password123");
        User charlie = new User("firstName", "lastname", "Charlie", "charlie@example.com", "password123");

        alice = userRepository.save(alice);
        bob = userRepository.save(bob);
        charlie = userRepository.save(charlie);

        // Create and save friendships
        // friendshipRepository.save(new Friendship(alice, bob)); // Alice <-> Bob
        // friendshipRepository.save(new Friendship(alice, charlie)); // Alice <-> Charlie

        // Act: Test the query
        boolean aliceAndBob = friendshipRepository.existsByUserIds(alice.getId(), bob.getId());
        boolean aliceAndCharlie = friendshipRepository.existsByUserIds(alice.getId(), charlie.getId());
        boolean bobAndCharlie = friendshipRepository.existsByUserIds(bob.getId(), charlie.getId());

        // Assert: Verify the results
        Assertions.assertTrue(aliceAndBob, "Alice and Bob should be friends");
        Assertions.assertTrue(aliceAndCharlie, "Alice and Charlie should be friends");
        Assertions.assertFalse(bobAndCharlie, "Bob and Charlie should not be friends");
    }
}
