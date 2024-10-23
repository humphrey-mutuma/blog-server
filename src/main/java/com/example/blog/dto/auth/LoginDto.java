package com.example.blog.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
     @NotNull
     @Email
     @NotBlank
     private String email;
     @NotNull
     @NotBlank
     @Size(min = 5, message = "Password is too short")
     private String password;
}
