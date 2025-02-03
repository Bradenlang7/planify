package com.plannr.dto;

import com.plannr.entity.Approval;
import org.springframework.stereotype.Service;

@Service
public class ApprovalMapper {
    private final UserMapper userMapper;

    public ApprovalMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ApprovalDTO toApprovalDTO(Approval approval) {
        return new ApprovalDTO(
                approval.getId(),
                approval.getStatus(),
                userMapper.toBaseUserDto(approval.getUser()) // Map the approver (User) to UserDTO
        );
    }

}
