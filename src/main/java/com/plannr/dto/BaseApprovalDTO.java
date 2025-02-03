package com.plannr.dto;

import com.plannr.enums.ApprovalStatusEnum;

public record BaseApprovalDTO(
        Long id,
        ApprovalStatusEnum status,
        Long userId,
        String userName
) {
}
