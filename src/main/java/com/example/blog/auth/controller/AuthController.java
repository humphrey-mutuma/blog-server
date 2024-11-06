package com.example.blog.auth.controller;

import com.example.blog.auth.dto.LoginDto;
import com.example.blog.auth.dto.LoginResponseDto;
import com.example.blog.auth.dto.RegisterDto;
import com.example.blog.auth.service.AuthService;
import com.example.blog.response.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDto registerDto) throws BadRequestException {
        try {
            String message = authService.register(registerDto);
            return ResponseEntity.ok(new ApiResponse<>(message, null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Registration failed: " + e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginDto loginDto) {
        try {
            LoginResponseDto response = authService.login(loginDto);
            return ResponseEntity.ok(new ApiResponse<>("Login successful", response));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("Invalid credentials. Please check your username or password.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( new ApiResponse<>("An error occurred during login. Please try again later.", null));
        }
    }
}
