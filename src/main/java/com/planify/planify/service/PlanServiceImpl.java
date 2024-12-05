package com.planify.planify.service;

import com.planify.planify.dto.PlanDTO;
import com.planify.planify.dto.PlanMapper;
import com.planify.planify.entity.Plan;
import com.planify.planify.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    public PlanServiceImpl(PlanRepository planRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
    }

    @Override
    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan updatePlan(Plan plan) {
        Plan existingPlan = planRepository.findById(plan.getId())
                .orElseThrow(() -> new IllegalStateException("Plan not found with id: " + plan.getId()));

        existingPlan.setTitle(plan.getTitle());
        existingPlan.setDescription(plan.getDescription());
        existingPlan.setLocation(plan.getLocation());
        existingPlan.setStartTime(plan.getStartTime());
        existingPlan.setEndTime(plan.getEndTime());

        return planRepository.save(existingPlan);

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
