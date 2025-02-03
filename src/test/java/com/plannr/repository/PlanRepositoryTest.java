package com.plannr.repository;

import com.plannr.dto.PlanDTO;
import com.plannr.entity.Plan;
import com.plannr.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PlanRepositoryTest {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void planRepository_FindPlanProjection_ReturnsPlanDTO() {
        User user = new User();

        user.setFirstname("Alice");
        user.setLastname("Smith");
        user.setUsername("alicesmith");
        user.setEmail("alice@example.com");
        user.setPassword("securepassword");
        userRepository.save(user);

        Plan plan = new Plan();

        plan.setCreator(user);
        plan.setDescription("This is a plan");
        plan.setTitle("Test");
        plan.setLocation("Test Location");
        plan.setStartTime(ZonedDateTime.now(ZoneId.of("America/New_York")));
        Plan savedPlan = planRepository.save(plan);

        Optional<PlanDTO> optionalPlanDTO = planRepository.findPlanProjection(savedPlan.getId());

        assertTrue(optionalPlanDTO.isPresent());

        PlanDTO planDTO = optionalPlanDTO.get();

        assertEquals(savedPlan.getId(), planDTO.getId());
        assertEquals("Test", planDTO.getTitle());
        assertEquals("This is a plan", planDTO.getDescription());
        assertEquals("Test Location", planDTO.getLocation());
        //assertEquals(savedPlan.getStartTime(), planDTO.getStartTime());
        assertEquals(savedPlan.getEndTime(), planDTO.getEndTime());
        assertEquals(user.getId(), planDTO.getCreatorId());
        assertEquals(user.getUsername(), planDTO.getCreatorUserName());
    }

}
