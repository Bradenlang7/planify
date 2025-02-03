package com.plannr.dto;

import com.plannr.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {

    private final UserMapper userMapper;

    public CommentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CommentDTO toCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCommenter().getUsername()// Map the commenter (User) to UserDTO
        );
    }
}
