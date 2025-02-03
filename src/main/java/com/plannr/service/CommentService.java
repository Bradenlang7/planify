package com.plannr.service;

import com.plannr.dto.CommentDTO;
import com.plannr.dto.CreateCommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO createComment(CreateCommentDTO createcommentDTO, Long id);

    public void deleteComment(Long commentId);

    public List<CommentDTO> getCommentsByPlanId(Long planId);
}
