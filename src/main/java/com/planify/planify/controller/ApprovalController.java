package com.planify.planify.controller;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.enums.ApprovalStatusEnum;
import com.planify.planify.service.ApprovalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/users/{userId}/plans/{planId}")
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
            @PathVariable ApprovalStatusEnum status,
            @RequestParam(required = false, defaultValue = "false") boolean includeOwner) {

        //Retrieve the UserId from the SecurityContext
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userId == null) {
            // Return an error response if userId is missing or invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Include owner==true will return all plans owned by user in addition to plans with the specified status
        List<BasePlanDTO> plans = approvalService.getPlansByUserIdAndStatus(userId, status, includeOwner);

        return ResponseEntity.ok(plans);

    }

}


