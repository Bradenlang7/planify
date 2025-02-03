package com.plannr.service;

import com.plannr.dto.BaseApprovalDTO;
import com.plannr.dto.BasePlanDTO;
import com.plannr.entity.Approval;
import com.plannr.enums.ApprovalStatusEnum;

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
