package com.planify.planify.service;

import com.planify.planify.dto.CommentDTO;
import com.planify.planify.dto.CreateCommentDTO;

public interface CommentService {
    public CommentDTO createComment(CreateCommentDTO createcommentDTO);

    public void deleteComment(Long commentId);
}
