package com.planify.planify.service;

import com.planify.planify.dto.*;
import com.planify.planify.entity.Plan;
import com.planify.planify.entity.User;
import com.planify.planify.repository.PlanRepository;
import com.planify.planify.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import({PlanMapper.class, UserMapper.class, CommentMapper.class, ApprovalMapper.class})
// Import service and mapper
public class PlanServiceImplTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanServiceImpl planService;

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        User creator = new User();
        creator.setUsername("johndoe");
        creator.setEmail("johndoe@example.com");
        creator.setFirstname("John");
        creator.setLastname("Doe");
        creator.setPassword("a;sldkfl");

// Save the user first
        User savedCreator = userRepository.save(creator);

// Now associate the user with the plan
        Plan plan = new Plan();
        plan.setTitle("Weekend Getaway");
        plan.setDescription("Plan for a weekend hiking trip.");
        plan.setLocation("Grand Canyon");
        plan.setStartTime(ZonedDateTime.now().plusDays(1));
        plan.setEndTime(ZonedDateTime.now().plusDays(2));
        plan.setCreator(savedCreator); // Set the saved user

// Save the plan
        planRepository.save(plan);
    }

    @Test
    void testGetPlanWithApprovalsAndComments() {
        // Find the plan ID from the repository
        Plan savedPlan = planRepository.findAll().get(0);

        // Act: Call the method under test
        PlanDTO result = planService.getPlanWithApprovalsAndComments(savedPlan.getId());

        // Assert: Verify the returned DTO
        assertNotNull(result, "The result should not be null.");
        assertEquals(savedPlan.getTitle(), result.title(), "The title should match.");
        assertEquals(savedPlan.getDescription(), result.description(), "The description should match.");
        assertEquals(savedPlan.getCreator().getUsername(), result.creator().username(), "The creator's username should match.");
    }
}