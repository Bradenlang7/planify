package com.planify.planify.dto;

import java.time.ZonedDateTime;

//Base Plan DTO sends minimum data to front end for use after get request or update
public record BasePlanDTO(
        Long id,
        String creatorUserName,
        String title,
        String description,
        String location,
        ZonedDateTime startTime,
        ZonedDateTime endTime
) {
}
