package com.planify.planify.service;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.dto.CreatePlanDTO;
import com.planify.planify.dto.PlanDTO;
import com.planify.planify.dto.UpdatePlanDTO;
import com.planify.planify.entity.Plan;

import java.util.List;


public interface PlanService {
    BasePlanDTO createPlan(CreatePlanDTO createPlanDTO);

    BasePlanDTO updatePlan(long id, UpdatePlanDTO updatePlanDTO);

    Plan getPlanById(long id);

    void deletePlan(long planId);

    List<Plan> getCreatorPlansByUserId(long userId);

    Plan getPlanWithApprovalsAndUsers(long planId);

    PlanDTO getPlanWithApprovalsUsersAndComments(long planId);

}
