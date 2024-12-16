package com.planify.planify;

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


    @Bean
    public CommandLineRunner testQuery(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        return args -> {

        };
    }


}
