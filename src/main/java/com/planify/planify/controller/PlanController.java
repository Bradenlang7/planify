package com.planify.planify.controller;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.dto.CreatePlanDTO;
import com.planify.planify.dto.PlanDTO;
import com.planify.planify.dto.UpdatePlanDTO;
import com.planify.planify.jwt.JwtUtil;
import com.planify.planify.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;
    private final JwtUtil jwtUtil;

    public PlanController(PlanService planService, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<BasePlanDTO> createPlan(@RequestBody CreatePlanDTO createPlanDTO, @RequestHeader("Authorization") String authHeader) {
        System.out.println("CREATE PLAN DTO: " + createPlanDTO);
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        Long id = Long.valueOf(jwtUtil.extractUserId(token));

        BasePlanDTO basePlanDTO = planService.createPlan(createPlanDTO, id);

        return ResponseEntity.ok(basePlanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable long id) {
        planService.deletePlan(id);
        return ResponseEntity.ok("Deleted plan with id " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasePlanDTO> updatePlan(@PathVariable long id, @RequestBody UpdatePlanDTO updatePlanDTO) {
        BasePlanDTO updatedBasePlan = planService.updatePlan(id, updatePlanDTO);

        return ResponseEntity.ok(updatedBasePlan);
    }

    // Retrieves a Plan with all nested dependencies
    @GetMapping("/{id}/details")
    public ResponseEntity<PlanDTO> getPlanWithDependenciesAll(@PathVariable long id) {
        PlanDTO planDTO = planService.getPlanWithApprovalsUsersAndComments(id);

        return ResponseEntity.ok(planDTO);
    }
}
