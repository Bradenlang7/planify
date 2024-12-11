package com.planify.planify.repository;

import com.planify.planify.dto.BasePlanDTO;
import com.planify.planify.entity.Approval;
import com.planify.planify.enums.ApprovalStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {


    //Query returns a list of approvals and associated plan objects based on a given userId
    @Query("SELECT a FROM Approval a JOIN FETCH a.plan p WHERE a.user.id = :userId")
    List<Approval> findApprovalsByUserId(@Param("userId") Long userId);

    //Query returns a list of plan entities belonging to a user filtered by status.
    @Query("""
                SELECT new com.planify.planify.dto.BasePlanDTO(
                    p.id,
                    u.username,
                    p.title,
                    p.description,
                    p.location,
                    p.startTime,
                    p.endTime
                )
                FROM Approval a
                JOIN a.plan p
                JOIN p.creator u
                WHERE a.user.id = :userId AND a.status = :status
            """)
    List<BasePlanDTO> findPlansByUserIdAndStatus(@Param("userId") Long userId, @Param("status") ApprovalStatusEnum status);


}
