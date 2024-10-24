package com.example.blog.service.article;

import com.example.blog.model.Article;

import java.util.List;

public interface IArticleService {
    // Retrieve an article by its ID
    Article getArticleById(Long articleId);
    // Create a new article
    Article createArticle(Article article);
    // Update an existing article
    Article updateArticle(Article updatedArticle, Long articleId);
    // Delete an article by its ID
    Long deleteArticleById(Long articleId);
    // Get all articles
    List<Article> getAllArticles(int page);

    //  Get articles by Tags
    List<Article> getArticlesByTag(String tag);

//    ...
}
