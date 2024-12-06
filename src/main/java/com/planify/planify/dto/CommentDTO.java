package com.planify.planify.dto;

public record CommentDTO(
        Long id,
        String content,
        BaseUserDTO commenter // Closely coupled with commenter
) {
}
