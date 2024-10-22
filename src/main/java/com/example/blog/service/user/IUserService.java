package com.example.blog.service.user;

import com.example.blog.model.User;

public interface IUserService {
    User getUserByEmail(String email);
    User updateUser(User user, Long userId);
    void  deleteUserById(Long userId);
}
