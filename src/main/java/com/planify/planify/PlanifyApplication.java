package com.planify.planify;

import com.planify.planify.entity.User;
import com.planify.planify.repository.FriendshipRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PlanifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanifyApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        return args -> {

            // Fetch all friends for user1 (Alice)
            List<User> friendsOfAlice = friendshipRepository.findFriendsByUserId(38L);

            // Print results
            System.out.println("Friends of Alice:");
            for (User friend : friendsOfAlice) {
                System.out.println(friend);
            }

        };
    }


}
