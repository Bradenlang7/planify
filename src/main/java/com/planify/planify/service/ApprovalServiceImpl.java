package com.planify.planify.service;

import com.planify.planify.entity.Approval;
import com.planify.planify.entity.Plan;
import com.planify.planify.entity.User;
import com.planify.planify.repository.ApprovalRepository;
import com.planify.planify.repository.PlanRepository;
import com.planify.planify.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService {


    private ApprovalRepository approvalRepository;
    private PlanRepository planRepository;
    private UserRepository userRepository;

    ApprovalServiceImpl(ApprovalRepository approvalRepository, PlanRepository planRepository, UserRepository userRepository) {
        this.approvalRepository = approvalRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<Approval> getApprovalsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return user.getApprovals();
    }

    @Transactional
    @Override
    public List<Approval> getApprovalsByPlanId(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with ID: " + planId));
        return plan.getApprovals();
    }

    @Override
    public Approval getApprovalById(Long approvalId) {
        Approval approval = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new IllegalStateException("Approval not found"));
        return approval;
    }

    @Override
    public Approval createApproval(Long userId, Long planId) {
        // Retrieve the user and plan from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalStateException("Plan not found with ID: " + planId));

        // Create the approval and set relationships
        Approval approval = new Approval(plan, user);

        // Save the approval
        return approvalRepository.save(approval);
    }

    @Override
    public Approval deleteApproval(Long approvalId) {
        Approval approval = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new IllegalStateException("Approval not found with ID: " + approvalId));
        approvalRepository.delete(approval);
        return approval;
    }


}
