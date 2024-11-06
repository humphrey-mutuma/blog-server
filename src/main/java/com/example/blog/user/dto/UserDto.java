package com.example.blog.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    @NotBlank
     private Long id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String image;


}
