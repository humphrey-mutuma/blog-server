package com.example.blog.post.dto;

import com.example.blog.Tag.entity.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String description;
    private Set<Tag> tags;
    private LocalDateTime createdAt;
}
