package com.planify.planify.service;

import com.planify.planify.dto.CommentDTO;
import com.planify.planify.dto.CreateCommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO createComment(CreateCommentDTO createcommentDTO, Long id);

    public void deleteComment(Long commentId);

    public List<CommentDTO> getCommentsByPlanId(Long planId);
}
