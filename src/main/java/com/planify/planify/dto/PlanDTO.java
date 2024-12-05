package com.planify.planify.dto;

import java.time.ZonedDateTime;
import java.util.List;

//DTO record for the Plan domain entity
public record PlanDTO(
        Long id,
        BaseUserDTO creator,
        String title,
        String description,
        String location,
        ZonedDateTime startTime,
        ZonedDateTime endTime,
        List<CommentDTO> comments,
        List<ApprovalDTO> approvals
) {
}
