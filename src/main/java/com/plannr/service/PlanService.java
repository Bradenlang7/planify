package com.plannr.service;

import com.plannr.dto.BasePlanDTO;
import com.plannr.dto.CreatePlanDTO;
import com.plannr.dto.PlanDTO;
import com.plannr.dto.UpdatePlanDTO;
import com.plannr.entity.Plan;

import java.util.List;


public interface PlanService {
    BasePlanDTO createPlan(CreatePlanDTO createPlanDTO, long id);

    BasePlanDTO updatePlan(long id, UpdatePlanDTO updatePlanDTO);

    Plan getPlanById(long id);

    void deletePlan(long planId);

    List<Plan> getCreatorPlansByUserId(long userId);

    Plan getPlanWithApprovalsAndUsers(long planId);

    PlanDTO getPlanWithApprovalsUsersAndComments(long planId);

}
