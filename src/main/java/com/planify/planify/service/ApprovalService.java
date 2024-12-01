package com.planify.planify.service;

import com.planify.planify.entity.Approval;

import java.util.List;

public interface ApprovalService {
    List<Approval> getApprovalsByUserId(Long userId);

    List<Approval> getApprovalsByPlanId(Long planId);

    Approval getApprovalById(Long id);

    Approval createApproval(Long userId, Long planId);

    Approval deleteApproval(Long approvalId);

}
