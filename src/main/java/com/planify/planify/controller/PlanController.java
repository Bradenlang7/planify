package com.planify.planify.controller;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.dto.CreatePlanDTO;
import com.planify.planify.dto.PlanDTO;
import com.planify.planify.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<BasePlanDTO> createPlan(@RequestBody CreatePlanDTO createPlanDTO) {
        BasePlanDTO basePlanDTO = planService.createPlan(createPlanDTO);

        return ResponseEntity.ok(basePlanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable long id) {
        planService.deletePlan(id);
        return ResponseEntity.ok("Deleted plan with id " + id);
    }

    @PutMapping
    public ResponseEntity<BasePlanDTO> updatePlan(@RequestBody BasePlanDTO basePlanDTO) {
        BasePlanDTO updatedBasePlan = planService.updatePlan(basePlanDTO);

        return ResponseEntity.ok(updatedBasePlan);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<PlanDTO> getPlanWithDependenciesAll(@PathVariable long id) {
        PlanDTO planDTO = planService.getPlanWithApprovalsUsersAndComments(id);

        return ResponseEntity.ok(planDTO);
    }
}
