package com.example.blog.comment.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotNull
    @NotBlank
    private String title;

//    @NotNull
    @NotBlank
    private Long userId;

//    @NotNull
    @NotBlank
    private Long postId;
}
