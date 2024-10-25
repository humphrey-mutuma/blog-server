package com.example.blog.dto.article;

import com.example.blog.model.Comment;
import com.example.blog.model.Tag;
import com.example.blog.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class CreateArticleDto {
    @NotNull
    @NotBlank(message = "Title is required!")
    @Size(min = 2, max = 200, message = "Title must be more than 2 characters and greater than 200 characters")
    private String title;

    @NotBlank(message = "Description is required!")
    @Size(min = 2, max = 2000, message = "Desc must be more than 2 chars and less than 2000 chars")
    private String description;

    @NotNull
    @NotBlank
    private Long userId;

//    private List<Long> commentId;
    private Set<Long> tagIds;

}
