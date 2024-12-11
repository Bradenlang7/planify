package com.planify.planify.service;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.dto.PlanMapper;
import com.planify.planify.entity.Approval;
import com.planify.planify.entity.Plan;
import com.planify.planify.entity.User;
import com.planify.planify.enums.ApprovalStatusEnum;
import com.planify.planify.repository.ApprovalRepository;
import com.planify.planify.repository.PlanRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService {


    private final PlanMapper planMapper;
    private ApprovalRepository approvalRepository;
    private PlanRepository planRepository;
    private UserRepository userRepository;

    ApprovalServiceImpl(ApprovalRepository approvalRepository, PlanRepository planRepository, UserRepository userRepository, PlanMapper planMapper) {
        this.approvalRepository = approvalRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
        this.planMapper = planMapper;
    }

    @Transactional
    @Override
    public List<Approval> getApprovalsByUserId(Long userId) {
        // Check if the user exists
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        // Fetch approvals for the user
        List<Approval> approvals = approvalRepository.findApprovalsByUserId(userId);

        return approvals;
    }

    @Override
    public Approval getApprovalById(Long approvalId) {
        Approval approval = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new IllegalStateException("Approval not found"));
        return approval;
    }

    @Transactional
    @Override
    public Approval createApproval(Long userId, Long planId) {
        // Retrieve the user and plan from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalStateException("Plan not found with ID: " + planId));

        // Set relationships
        Approval approval = new Approval(plan, user);

        return approvalRepository.save(approval);
    }

    @Transactional
    @Override
    public Approval deleteApproval(Long approvalId) {
        Approval approval = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new IllegalStateException("Approval not found with ID: " + approvalId));
        approvalRepository.delete(approval);
        return approval;
    }

    @Transactional
    @Override
    public List<BasePlanDTO> getPlansByUserIdAndStatus(Long userId, ApprovalStatusEnum status) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        return approvalRepository.findPlansByUserIdAndStatus(userId, status);
    }


}
