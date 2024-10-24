package com.example.blog.service.auth;

import com.example.blog.model.User;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{
//    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public User register(String email, String password, String username) {
        return null;
    }

    @Override
    public User resetPassword(String email, String newPassword) {
        return null;
    }

    @Override
    public String generateAccessToken(User user) {
        return "";
    }

    @Override
    public String generateRefreshToken(User user) {
        return "";
    }

    @Override
    public String refreshAccessToken(String refreshToken) {
        return "";
    }

    @Override
    public void logOut(String token) {

    }
}
