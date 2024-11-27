package com.planify.planify.service;

import com.planify.planify.entity.Plan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public Plan CreatePlan(Plan plan) {
        return null;
    }

    @Override
    public Plan UpdatePlan(Plan plan) {
        return null;
    }

    @Override
    public void DeletePlan(Plan plan) {

    }

    @Override
    public List<Plan> GetPlansByUserId(long userId) {
        return List.of();
    }

    @Override
    public Optional<Plan> getPlanWithApprovals(long planId) {
        return Optional.empty();
    }

    @Override
    public Optional<Plan> getPlanWithApprovalsAndComments(long planId) {
        return Optional.empty();
    }
}
