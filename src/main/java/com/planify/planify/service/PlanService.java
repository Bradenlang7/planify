package com.planify.planify.service;

import com.planify.planify.entity.Plan;

import java.util.List;
import java.util.Optional;


public interface PlanService {
    Plan CreatePlan(Plan plan);

    Plan UpdatePlan(Plan plan);

    void DeletePlan(Plan plan);

    Optional<Plan> GetPlanById(long id);

    List<Plan> GetPlansByUserId(Long userId);


}
