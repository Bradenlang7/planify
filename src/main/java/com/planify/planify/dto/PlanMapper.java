package com.planify.planify.dto;

import com.planify.planify.entity.Plan;
import org.springframework.stereotype.Service;

@Service
public class PlanMapper {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final ApprovalMapper approvalMapper;

    public PlanMapper(UserMapper userMapper, CommentMapper commentMapper, ApprovalMapper approvalMapper) {
        //Inject userMapper instance to convert User field in Plan
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.approvalMapper = approvalMapper;
    }

    //Maps plan to DTO object. May need to refactor due to specificity
    /*
    public PlanDTO toPlanDto(Plan plan) {
        return new PlanDTO(
                plan.getId(),
                userMapper.toBaseUserDto(plan.getCreator()), // Map the User entity to a UserDTO
                plan.getTitle(),
                plan.getDescription(),
                plan.getLocation(),
                plan.getStartTime(),
                plan.getEndTime(),
                plan.getComments().stream()
                        .map(commentMapper::toCommentDTO) // Map each Comment entity to CommentDTO
                        .collect(Collectors.toList()),
                plan.getApprovals().stream()
                        .map(approvalMapper::toApprovalDTO) // Map each Approval entity to ApprovalDTO
                        .collect(Collectors.toList())
        );
    }

     */

    public BasePlanDTO toBasePlanDto(Plan plan) {
        return new BasePlanDTO(
                plan.getId(),
                plan.getCreator().getUsername(), // Map the User entity to a UserDTO
                plan.getTitle(),
                plan.getDescription(),
                plan.getLocation(),
                plan.getStartTime(),
                plan.getEndTime()
        );
    }

    //Converts to Plan entity
    public Plan toPlan(CreatePlanDTO createPlanDTO) {
        Plan plan = new Plan();

        plan.setTitle(createPlanDTO.title());
        plan.setDescription(createPlanDTO.description());
        plan.setLocation(createPlanDTO.location());
        plan.setStartTime(createPlanDTO.startTime());
        plan.setEndTime(createPlanDTO.endTime());
        //Creator to be set in the service layer

        return plan;
    }
}
