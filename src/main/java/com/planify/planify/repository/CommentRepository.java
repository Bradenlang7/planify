package com.planify.planify.repository;

import com.planify.planify.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Find all comments for a specific plan
    List<Comment> findByPlanId(Long planId);

    // Fetch comments with their commenters
    @Query("SELECT c FROM Comment c JOIN FETCH c.commenter WHERE c.plan.id = :planId")
    List<Comment> findCommentsWithCommentersByPlanId(@Param("planId") Long planId);
}
