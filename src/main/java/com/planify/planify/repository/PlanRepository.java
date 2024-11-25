package com.planify.planify.repository;

import com.planify.planify.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // Find all plans by creator ID
    List<Plan> findByCreatorId(Long creatorId);

    
}
