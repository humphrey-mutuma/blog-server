package com.example.blog.service.user;

import com.example.blog.model.Article;
import com.example.blog.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserService {
//    get a user by email
   Optional<User> getUserByEmail(String email);
//    update user details
    User updateUser(User user, Long userId);
//    delete user
    void  deleteUserById(Long userId);
//    bookmark an article
    boolean bookmarkArticle(Long articleId);
//    get user bookmarks
    List<Long> getUserBookmarks(Long userid);
//
}
