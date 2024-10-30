package com.example.blog.service.user;

import com.example.blog.dto.user.UserDto;
import com.example.blog.model.Article;
import com.example.blog.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
//    get a user by email
   Optional<UserDto> getUserByEmail(String email);
//    update user details
    UserDto updateUser(User user, Long userId);
//    delete user
    void   deleteUserById(Long userId);
//    bookmark an article
    boolean bookmarkArticle(Long articleId);

    boolean bookmarkArticle(Long userId, Long articleId);

    //    get user bookmarks
    List<Article> getUserBookmarks(Long userid);
    //    get a users articles
    List<Article> getUserArticles(Long userId);
    //    get users articles count
    int getUsersArticlesCount(Long userId);
//
}
