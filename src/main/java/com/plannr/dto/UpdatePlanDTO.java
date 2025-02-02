package com.plannr.dto;

import java.time.ZonedDateTime;

public record UpdatePlanDTO(
        String creatorUserName,
        String title,
        String description,
        String location,
        ZonedDateTime startTime,
        ZonedDateTime endTime
) {
}
