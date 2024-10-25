package com.example.blog.dto.comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommentDto {

    @NotNull
    @NotBlank
    private String title;

//    @NotNull
    @NotBlank
    private Long userId;

//    @NotNull
    @NotBlank
    private Long articleId;
}
