package com.plannr.repository;

import com.plannr.dto.CommentDTO;
import com.plannr.entity.Comment;
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
    @Query("""
                SELECT new com.plannr.dto.CommentDTO(
                    c.id,
                    c.content,
                    u.username
                )
                FROM Comment c
                JOIN c.commenter u
                WHERE c.plan.id = :planId
            """)
    List<CommentDTO> findCommentsWithCommentersByPlanId(@Param("planId") Long planId);
}
