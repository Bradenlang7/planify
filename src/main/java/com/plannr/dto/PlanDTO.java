package com.plannr.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

//DTO class for the Plan domain entity
@Data
public class PlanDTO {
    private Long id;
    private Long creatorId;
    private String creatorUserName;
    private String title;
    private String description;
    private String location;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private List<CommentDTO> comments;
    private List<BaseApprovalDTO> approvals;


    public PlanDTO(Long id, String title, String description,
                   String location, ZonedDateTime startTime, ZonedDateTime endTime, Long creatorId, String creatorUserName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creatorId = creatorId;
        this.creatorUserName = creatorUserName;
    }
}
