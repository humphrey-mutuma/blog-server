package com.example.blog.dto.comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCommentResDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
