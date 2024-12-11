package com.planify.planify.controller;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.entity.Approval;
import com.planify.planify.enums.ApprovalStatusEnum;
import com.planify.planify.service.ApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/approvals")
public class ApprovalController {
    private final ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @PostMapping("/users{userId}/plans{planId}")
    public ResponseEntity<Approval> createApproval(@PathVariable Long userId, @PathVariable Long planId) {
        Approval approval = approvalService.createApproval(planId, userId);

        return ResponseEntity.ok(approval);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Approval> deleteApproval(@PathVariable Long id) {
        Approval approval = approvalService.deleteApproval(id);

        return ResponseEntity.ok(approval);
    }

    @GetMapping("/users/{userId}/status{status}")
    public ResponseEntity<List<BasePlanDTO>> getPlansByUserIdAndStatus(@PathVariable Long userId,
                                                                       @PathVariable ApprovalStatusEnum status,
                                                                       @RequestParam(required = false, defaultValue = "false") boolean includeOwner) {
        List<BasePlanDTO> plans = approvalService.getPlansByUserIdAndStatus(userId, status, includeOwner);// Include owner==true will return all plans owned by user in addition to plans with the specified status

        return ResponseEntity.ok(plans);

    }

}


