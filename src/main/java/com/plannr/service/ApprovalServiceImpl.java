package com.plannr.service;

import com.plannr.dto.BaseApprovalDTO;
import com.plannr.dto.BasePlanDTO;
import com.plannr.dto.PlanMapper;
import com.plannr.entity.Approval;
import com.plannr.entity.Plan;
import com.plannr.entity.User;
import com.plannr.enums.ApprovalStatusEnum;
import com.plannr.repository.ApprovalRepository;
import com.plannr.repository.PlanRepository;
import com.plannr.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService {


    private final PlanMapper planMapper;
    private final ApprovalRepository approvalRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

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


    @Transactional
    @Override
    public void createApproval(Long userId, Long planId) {
        //********************NEED TO ADD CHECK IF APPROVAL EXISTS IN DB******************
        // Retrieve the user and plan from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalStateException("Plan not found with ID: " + planId));

        // Set relationships
        Approval approval = new Approval(plan, user);

        approvalRepository.save(approval);
    }

    @Transactional
    @Override
    public String updateApproval(Long approvalId, boolean accepted) {
        Approval approval = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new IllegalStateException("Approval not found with ID: " + approvalId));

        if (accepted) {
            approval.setStatus(ApprovalStatusEnum.APPROVED);
        } else {
            approval.setStatus(ApprovalStatusEnum.REJECTED);
        }

        approvalRepository.save(approval);

        //Return for use in ApprovalController method
        return accepted ? "Accepted" : "Rejected";
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
    public List<BasePlanDTO> getPlansByUserIdAndStatus(Long userId, ApprovalStatusEnum status, boolean includeOwner) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        System.out.println(includeOwner);
        return approvalRepository.findPlansByUserIdAndStatus(userId, status, includeOwner);
    }

    @Transactional
    @Override
    public Approval persistApproval(Approval approval) {
        return approvalRepository.save(approval);
    }

    @Transactional
    @Override
    public List<BaseApprovalDTO> getApprovalsByPlanIdProjection(long planId) {
        List<BaseApprovalDTO> approvals = approvalRepository.findBaseApprovalsByPlanIdProjection(planId);

        return approvals != null ? approvals : Collections.emptyList();
    }


}
