package com.plannr.dto;

public record CommentDTO(
        Long id,
        String content,
        String commenterUserName // Closely coupled with commenter
) {
}
