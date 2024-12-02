package com.planify.planify.service;

import com.planify.planify.entity.Comment;
import com.planify.planify.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
