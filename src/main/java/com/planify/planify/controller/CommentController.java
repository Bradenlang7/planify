package com.planify.planify.controller;

import com.planify.planify.dto.CommentDTO;
import com.planify.planify.dto.CreateCommentDTO;
import com.planify.planify.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CreateCommentDTO createCommentDTO) {
        CommentDTO commentDTO = commentService.createComment(createCommentDTO);

        return ResponseEntity.ok(commentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity.ok("Comment with " + id + " was deleted");
    }

    @GetMapping("/plans{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsWithPlanId(@PathVariable Long planId) {
        List<CommentDTO> comments = commentService.getCommentsByPlanId(planId);

        return ResponseEntity.ok(comments);
    }
}
