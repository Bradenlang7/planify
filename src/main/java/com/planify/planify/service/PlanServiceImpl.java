package com.planify.planify.service;

import com.planify.planify.dto.*;
import com.planify.planify.entity.Approval;
import com.planify.planify.entity.Plan;
import com.planify.planify.entity.User;
import com.planify.planify.enums.ApprovalStatusEnum;
import com.planify.planify.repository.PlanRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;
    private final UserService userService;
    private final ApprovalService approvalService;
    private final CommentService commentService;

    public PlanServiceImpl(PlanRepository planRepository, PlanMapper planMapper, UserService userService, ApprovalService approvalService, @Lazy CommentService commentService) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
        this.userService = userService;
        this.approvalService = approvalService;
        this.commentService = commentService;
    }

    @Override
    public BasePlanDTO createPlan(CreatePlanDTO createPlanDTO, long userId) {
        Plan plan = planMapper.toPlan(createPlanDTO);

        //get the plans associated user
        User user = userService.getUserById(userId);

        plan.setCreator(user);

        Plan savedPlan = planRepository.save(plan);

        //Create a new Approval entity for the Plan with User as OWNER
        Approval approval = new Approval(savedPlan, user);
        approval.setStatus(ApprovalStatusEnum.OWNER);
        approvalService.persistApproval(approval);


        return planMapper.toBasePlanDto(savedPlan);
    }

    @Override
    public BasePlanDTO updatePlan(long planId, UpdatePlanDTO updatePlanDTO) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalStateException("Plan not found with id: " + planId));
        if (updatePlanDTO.title() != null) {
            plan.setTitle(updatePlanDTO.title());
        }
        if (updatePlanDTO.description() != null) {
            plan.setDescription(updatePlanDTO.description());
        }
        if (updatePlanDTO.location() != null) {
            plan.setLocation(updatePlanDTO.location());
        }
        if (updatePlanDTO.startTime() != null) {
            plan.setStartTime(updatePlanDTO.startTime());
        }
        if (updatePlanDTO.endTime() != null) {
            plan.setEndTime(updatePlanDTO.endTime());
        }

        Plan updatedPlan = planRepository.save(plan);
        return planMapper.toBasePlanDto(updatedPlan);
    }

    @Override
    public Plan getPlanById(long id) {
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
        PlanDTO plan = planRepository.findPlanProjection(planId)
                .orElseThrow(() -> new IllegalStateException("Plan approvals not found with plan id: " + planId));

        List<CommentDTO> comments = commentService.getCommentsByPlanId(planId);
        List<BaseApprovalDTO> approvals = approvalService.getApprovalsByPlanIdProjection(planId);

        plan.setComments(comments);
        plan.setApprovals(approvals);

        return plan;
    }
}
