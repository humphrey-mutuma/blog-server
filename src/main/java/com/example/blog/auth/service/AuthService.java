package com.example.blog.auth.service;

import com.example.blog.auth.dto.LoginDto;
import com.example.blog.auth.dto.LoginResponseDto;
import com.example.blog.auth.dto.RegisterDto;
import com.example.blog.auth.repo.AuthRepository;
import com.example.blog.user.entity.User;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    AuthenticationManager authManager;
    private final JWTService jwtService;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public String register(RegisterDto user) {
        // Create a new User entity and set the properties from RegisterDto
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        // newUser.setRoles(user.getRoles()); // Assuming roles are set in RegisterDto

        // Save the new user to the database
        authRepository.save(newUser);
        return "Registration successful, please login";

    }

//    login user
    public LoginResponseDto login(LoginDto user) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (!authentication.isAuthenticated()) {
                throw new BadCredentialsException("Invalid credentials. Please check your username and password.");
            }
//            generate new token
            String token = jwtService.generateToken(user.getUsername());
            // Retrieve user details (assuming you have a user details service or repository)
            User user_from_db = authRepository.findByUsername(user.getUsername());
            // Create and return the LoginResponseDto with the token, email, and username
            return new LoginResponseDto(token, user.getUsername(), user_from_db.getImage());
        } catch (RuntimeException e) {
            // Return a response with a specific error message for invalid credentials
            throw new RuntimeException("Login failed! " + e.getMessage());
        }
    }
}
