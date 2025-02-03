package com.plannr.controller;

import com.plannr.dto.CommentDTO;
import com.plannr.dto.CreateCommentDTO;
import com.plannr.jwt.JwtUtil;
import com.plannr.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final SimpMessageSendingOperations messagingTemplate;


    public CommentController(CommentService commentService, JwtUtil jwtUtil, SimpMessageSendingOperations messagingTemplate) {
        this.commentService = commentService;
        this.jwtUtil = jwtUtil;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CreateCommentDTO createCommentDTO, @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        Long id = Long.valueOf(jwtUtil.extractUserId(token));

        CommentDTO commentDTO = commentService.createComment(createCommentDTO, id);

        Long planId = createCommentDTO.planId();

        Map<String, Object> message = Map.of(
                "action", "add",
                "data", commentDTO
        );

        System.out.println("message" + message);

        messagingTemplate.convertAndSend("/topic/comments/" + planId, message);

        System.out.println("Sent broadcast message");

        return ResponseEntity.ok("Comment successfully broadcast");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        messagingTemplate.convertAndSend("/topic/comments/delete", id);

        return ResponseEntity.ok("Comment with " + id + " was deleted");
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsWithPlanId(@PathVariable Long id) {
        List<CommentDTO> comments = commentService.getCommentsByPlanId(id);

        return ResponseEntity.ok(comments);
    }
}
