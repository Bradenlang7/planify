package com.planify.planify;

import com.planify.planify.entity.User;
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
    CommandLineRunner run(UserRepository userRepository) {
        return args -> {
            User mockUser = new User();
            mockUser.setUsername("mockuser");
            mockUser.setEmail("mockuser@example.com");
            mockUser.setPassword("password123");

            userRepository.save(mockUser);

            System.out.println("Mock user saved: " + mockUser);
        };
    }

}
