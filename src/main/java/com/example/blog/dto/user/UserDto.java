package com.example.blog.dto.user;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.Snippet;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    @NotBlank
     private Long id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String email;

}
