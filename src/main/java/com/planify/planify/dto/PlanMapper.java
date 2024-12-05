package com.planify.planify.dto;

import com.planify.planify.entity.Plan;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PlanMapper {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final ApprovalMapper approvalMapper;

    public PlanMapper(UserMapper userMapper, CommentMapper commentMapper, ApprovalMapper approvalMapper) {
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.approvalMapper = approvalMapper;
    }

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
}
