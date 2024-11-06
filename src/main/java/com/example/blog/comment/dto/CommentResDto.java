package com.example.blog.comment.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
