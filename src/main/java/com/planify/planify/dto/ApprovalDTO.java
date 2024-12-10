package com.planify.planify.dto;

import com.planify.planify.enums.ApprovalStatusEnum;

//ApprovalDTO used in PlanDTO to minimize data sent with PlanDTOAll
public record ApprovalDTO(
        Long id,
        ApprovalStatusEnum status,
        BaseUserDTO user
) {
}
