package com.example.blog.service.article;

import com.example.blog.model.Article;

import java.util.List;

public interface IArticleService {
    // Retrieve an article by its ID
    Article getArticleById(Long articleId);
    // Create a new article
    Article createArticle(Article article);
    // Update an existing article
    Article updateArticle(Article updatedArticle);
    // Delete an article by its ID
    void deleteArticleById(Long articleId);
    // Get all articles
    List<Article> getAllArticles();
    //  Get articles by Tags
    List<Article> getArticlesByTags(String tag);
    //    get a users articles
    List<Article> getUserArticles(Long userId);
    //    get users articles count
    long countUsersArticles(Long userId);
//    ...
}
