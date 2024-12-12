package com.planify.planify.dto;

import com.planify.planify.enums.ApprovalStatusEnum;

public record BaseApprovalDTO(
        Long id,
        ApprovalStatusEnum status,
        Long userId,
        String userName
) {
}
