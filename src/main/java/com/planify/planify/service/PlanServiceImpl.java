package com.planify.planify.service;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.dto.CreatePlanDTO;
import com.planify.planify.dto.PlanDTO;
import com.planify.planify.dto.PlanMapper;
import com.planify.planify.entity.Plan;
import com.planify.planify.entity.User;
import com.planify.planify.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;
    private final UserService userService;

    public PlanServiceImpl(PlanRepository planRepository, PlanMapper planMapper, UserService userService) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
        this.userService = userService;
    }

    @Override
    public BasePlanDTO createPlan(CreatePlanDTO createPlanDTO) {
        Plan plan = planMapper.toPlan(createPlanDTO);

        //get the plans associated user
        User user = userService.getUserById(createPlanDTO.creatorId());

        plan.setCreator(user);

        planRepository.save(plan);

        return planMapper.toBasePlanDto(plan);
    }

    @Override
    public BasePlanDTO updatePlan(BasePlanDTO basePlanDTO) {
        Plan plan = planRepository.findById(basePlanDTO.id())
                .orElseThrow(() -> new IllegalStateException("Plan not found with id: " + basePlanDTO.id()));
        if (basePlanDTO.title() != null) {
            plan.setTitle(basePlanDTO.title());
        }
        if (basePlanDTO.description() != null) {
            plan.setDescription(basePlanDTO.description());
        }
        if (basePlanDTO.location() != null) {
            plan.setLocation(basePlanDTO.location());
        }
        if (basePlanDTO.startTime() != null) {
            plan.setStartTime(basePlanDTO.startTime());
        }
        if (basePlanDTO.endTime() != null) {
            plan.setEndTime(basePlanDTO.endTime());
        }

        Plan updatedPlan = planRepository.save(plan);
        return planMapper.toBasePlanDto(updatedPlan);
    }

    @Override
    public Plan getPlanById(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Plan not found with id: " + id));
        return plan;
    }

    @Override
    public void deletePlan(long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalStateException("Plan not found with id: " + planId));
        planRepository.delete(plan);
    }


    @Override
    public List<Plan> getCreatorPlansByUserId(long creatorId) {
        return planRepository.findByCreatorId(creatorId);
    }

    @Override
    public Plan getPlanWithApprovalsAndUsers(long planId) {
        return planRepository.findPlanWithApprovalsAndUsers(planId)
                .orElseThrow(() -> new IllegalStateException("Plan and approvals not found with plan id: " + planId));
    }

    @Override
    public PlanDTO getPlanWithApprovalsUsersAndComments(long planId) {
        Plan planWithApprovals = planRepository.findPlanWithApprovals(planId)
                .orElseThrow(() -> new IllegalStateException("Plan approvals not found with plan id: " + planId));
        Plan planWithComments = planRepository.findPlanWithComments(planId)
                .orElseThrow(() -> new IllegalStateException("Plan comments not found with plan id: " + planId));

        planWithApprovals.setComments(planWithComments.getComments());

        return planMapper.toPlanDto(planWithApprovals);
    }
}
