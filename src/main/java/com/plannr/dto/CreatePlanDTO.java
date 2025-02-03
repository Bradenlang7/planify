package com.plannr.dto;

import java.time.ZonedDateTime;
import java.util.List;

//DTO to be received by the client to create a User domain entity
public record CreatePlanDTO(
        String title,
        String description,
        String location,
        ZonedDateTime startTime,
        ZonedDateTime endTime,
        List<Long> invitees

) {
}
