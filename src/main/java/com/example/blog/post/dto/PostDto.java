package com.example.blog.post.dto;
import com.example.blog.comment.entity.Comment;
import com.example.blog.Tag.entity.Tag;
import com.example.blog.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotNull
    private Long id;
    @NotNull
    @NotBlank(message = "Title is required!")
    @Size(min = 2, max = 200, message = "Title must be more than 2 characters and greater than 200 characters")
    private String title;

    @NotBlank(message = "Description is required!")
    @Size(min = 2, max = 2000, message = "Desc must be more than 2 chars and less than 2000 chars")
    private String description;

    @NotNull
    @NotBlank
    private User user;

    private List<Comment> comments;
    private Set<Tag> tags;


}
