package com.plannr.repository;

import com.plannr.dto.BasePlanDTO;
import com.plannr.entity.Approval;
import com.plannr.entity.Plan;
import com.plannr.entity.User;
import com.plannr.enums.ApprovalStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ApprovalRepositoryTest {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApprovalRepository approvalRepository;

    @Test
    public void approvalRepository_FindPlansByUserIdAndStatus_ReturnsBasePlanDTO() {
        User user = new User();

        user.setFirstname("Alice");
        user.setLastname("Smith");
        user.setUsername("alicesmith");
        user.setEmail("alice@example.com");
        user.setPassword("securepassword");
        User savedUser = userRepository.save(user);

        Plan plan = new Plan();

        plan.setCreator(user);
        plan.setDescription("This is a plan");
        plan.setTitle("Test");
        plan.setLocation("Test Location");
        plan.setStartTime(ZonedDateTime.now(ZoneId.of("America/New_York")));
        Plan savedPlan = planRepository.save(plan);

        Approval approval = new Approval();

        approval.setUser(user);
        approval.setPlan(savedPlan);
        approval.setStatus(ApprovalStatusEnum.PENDING);
        Approval savedApproval = approvalRepository.save(approval);
        //ApprovalStatusEnum and includeOwner param can be swapped to change the results of the repo query
        List<BasePlanDTO> basePlanDTOList = approvalRepository.findPlansByUserIdAndStatus(savedUser.getId(), ApprovalStatusEnum.PENDING, false);

        assertTrue(basePlanDTOList.size() == 1);

        assertEquals(basePlanDTOList.get(0).id(), savedUser.getId());


    }

}

