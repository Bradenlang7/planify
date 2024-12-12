package com.planify.planify.service;

import com.planify.planify.dto.BaseApprovalDTO;
import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.entity.Approval;
import com.planify.planify.enums.ApprovalStatusEnum;

import java.util.List;

public interface ApprovalService {
    List<Approval> getApprovalsByUserId(Long userId);

    void createApproval(Long userId, Long planId);

    String updateApproval(Long approvalId, boolean accepted);

    Approval deleteApproval(Long approvalId);

    List<BasePlanDTO> getPlansByUserIdAndStatus(Long userId, ApprovalStatusEnum status, boolean includeOwner);

    Approval persistApproval(Approval approval);

    List<BaseApprovalDTO> getApprovalsByPlanIdProjection(long planId);
}
