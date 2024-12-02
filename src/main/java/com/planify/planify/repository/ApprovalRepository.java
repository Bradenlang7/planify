package com.planify.planify.repository;

import com.planify.planify.entity.Approval;
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

}
