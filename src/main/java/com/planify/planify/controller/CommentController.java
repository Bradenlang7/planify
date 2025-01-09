package com.planify.planify.controller;

import com.planify.planify.dto.CommentDTO;
import com.planify.planify.dto.CreateCommentDTO;
import com.planify.planify.jwt.JwtUtil;
import com.planify.planify.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;


    public CommentController(CommentService commentService, JwtUtil jwtUtil) {
        this.commentService = commentService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CreateCommentDTO createCommentDTO, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        Long id = Long.valueOf(jwtUtil.extractUserId(token));

        CommentDTO commentDTO = commentService.createComment(createCommentDTO, id);


        return ResponseEntity.ok(commentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity.ok("Comment with " + id + " was deleted");
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsWithPlanId(@PathVariable Long id) {
        List<CommentDTO> comments = commentService.getCommentsByPlanId(id);

        return ResponseEntity.ok(comments);
    }
}
