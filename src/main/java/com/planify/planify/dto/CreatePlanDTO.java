package com.planify.planify.dto;

import java.time.ZonedDateTime;

//DTO to be received by the client to create a User domain entity
public record CreatePlanDTO(
        Long creatorId,
        String title,
        String description,
        String location,
        ZonedDateTime startTime,
        ZonedDateTime endTime

) {
}
