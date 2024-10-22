package com.example.blog.service.auth;

import com.example.blog.model.User;

public interface IAuthService {
//    login a user by email and pass
    User login(String email, String password);
//   create accounts
    User register(String email, String password, String username);
//    reset users password
    User resetPassword(String email, String newPassword);
//    generate tokes
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
//    refresh access token
    String refreshAccessToken(String refreshToken);
//    logout
    void logOut(String token);
//   ....
}
