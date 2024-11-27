package com.planify.planify;

import com.planify.planify.entity.Friendship;
import com.planify.planify.entity.User;
import com.planify.planify.repository.FriendshipRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlanifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanifyApplication.class, args);
    }

    //******************DUMMY DATA FOR TESTING***********************

    @Bean
    public CommandLineRunner testQuery(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        return args -> {
            // Step 1: Create and save users
            User alice = new User("Alice", "alice@example.com", "password123");
            User bob = new User("Bob", "bob@example.com", "password123");
            User charlie = new User("Charlie", "charlie@example.com", "password123");
            userRepository.save(alice);
            userRepository.save(bob);
            userRepository.save(charlie);

            // Step 2: Create and save friendships
            friendshipRepository.save(new Friendship(alice, bob)); // Alice <-> Bob
            friendshipRepository.save(new Friendship(alice, charlie)); // Alice <-> Charlie

            // Step 3: Test the existsByUserIds query
            boolean aliceAndBob = friendshipRepository.existsByUserIds(alice.getId(), bob.getId());
            boolean aliceAndCharlie = friendshipRepository.existsByUserIds(alice.getId(), charlie.getId());
            boolean bobAndCharlie = friendshipRepository.existsByUserIds(bob.getId(), charlie.getId());

            // Step 4: Output the results
            System.out.println("Alice and Bob are friends: " + aliceAndBob); // Expected: true
            System.out.println("Alice and Charlie are friends: " + aliceAndCharlie); // Expected: true
            System.out.println("Bob and Charlie are friends: " + bobAndCharlie); // Expected: false
        };
    }


}
