package com.planify.planify.dto;

import com.planify.planify.enums.ApprovalStatusEnum;

public record ApprovalDTO(
        Long id,
        ApprovalStatusEnum status,
        BaseUserDTO user
) {
}
