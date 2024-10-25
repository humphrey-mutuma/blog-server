package com.example.blog.dto.article;

import com.example.blog.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String description;
    private Set<Tag> tags;

}
