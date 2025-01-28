package com.planify.planify.controller;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.enums.ApprovalStatusEnum;
import com.planify.planify.jwt.JwtUtil;
import com.planify.planify.service.ApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/approvals")
public class ApprovalController {
    private final ApprovalService approvalService;
    private final JwtUtil jwtUtil;

    public ApprovalController(ApprovalService approvalService, JwtUtil jwtUtil) {
        this.approvalService = approvalService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping()
    public ResponseEntity<String> createApproval(@PathVariable long userId, @PathVariable Long planId) {
        approvalService.createApproval(userId, planId);

        return ResponseEntity.ok("Approval created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApproval(@PathVariable long id) {
        approvalService.deleteApproval(id);

        return ResponseEntity.ok("Approval deleted");
    }

    @PutMapping("/{id}/{accepted}")
    public ResponseEntity<String> updateApproval(@PathVariable long id, @PathVariable boolean accepted) {
        String status = approvalService.updateApproval(id, accepted);

        return ResponseEntity.ok("Plan has been " + status);
    }

    @GetMapping("/users/status/{status}")
    public ResponseEntity<List<BasePlanDTO>> getPlansByUserIdAndStatus(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable ApprovalStatusEnum status,
            @RequestParam(required = false, defaultValue = "false") boolean includeOwner) {
        System.out.println("called getPlansByUserIdAndStatus");

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        Long userId = Long.valueOf(jwtUtil.extractUserId(token));


        // Include owner==true will return all plans owned by user in addition to plans with the specified status
        List<BasePlanDTO> plans = approvalService.getPlansByUserIdAndStatus(userId, status, includeOwner);

        return ResponseEntity.ok(plans);

    }

}


