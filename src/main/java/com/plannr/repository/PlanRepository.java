package com.plannr.repository;

import com.plannr.dto.PlanDTO;
import com.plannr.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // Find all plans by creator ID
    List<Plan> findByCreatorId(long creatorId);

    //query returns a plan along with all of its approvals and approvees
    @Query(
            "SELECT p FROM Plan p JOIN FETCH p.approvals a "
                    + "JOIN FETCH a.user WHERE p.id = :planId"
    )
    Optional<Plan> findPlanWithApprovalsAndUsers(@Param("planId") Long planId);

    //query returns a plan along with its associated approvals and users
    @Query("SELECT DISTINCT p FROM Plan p " +
            "JOIN FETCH p.approvals a " +
            "JOIN FETCH a.user " +
            "WHERE p.id = :planId")
    Optional<Plan> findPlanWithApprovalsAndUsers(@Param("planId") long planId);

    //query returns a plan with the plans associated approvals, users and comments
    @Query("SELECT DISTINCT p FROM Plan p " +
            "LEFT JOIN FETCH p.approvals a " +
            "WHERE p.id = :planId")
    Optional<Plan> findPlanWithApprovals(@Param("planId") long planId);

    @Query("SELECT DISTINCT p FROM Plan p " +
            "LEFT JOIN FETCH p.comments c " +
            "WHERE p.id = :planId")
    Optional<Plan> findPlanWithComments(@Param("planId") long planId);

    @Query("""
                SELECT new com.plannr.dto.PlanDTO(
                p.id,
                p.title,
                p.description,
                p.location,
                p.startTime,
                p.endTime,
                c.id,
                c.username
                )
                FROM Plan p
                JOIN p.creator c
                WHERE p.id = :planId
            """)
    Optional<PlanDTO> findPlanProjection(@Param("planId") long planId);


}
