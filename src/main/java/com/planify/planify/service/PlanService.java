package com.planify.planify.service;

import com.planify.planify.dto.PlanDTO;
import com.planify.planify.entity.Plan;

import java.util.List;


public interface PlanService {
    Plan createPlan(Plan plan);

    Plan updatePlan(Plan plan);

    void deletePlan(long planId);

    List<Plan> getCreatorPlansByUserId(long userId);

    Plan getPlanWithApprovalsAndUsers(long planId);

    PlanDTO getPlanWithApprovalsUsersAndComments(long planId);

}
