package com.example.blog.service.article;

import com.example.blog.dto.article.ArticleResponseDto;
import com.example.blog.dto.article.CreateArticleDto;
import com.example.blog.model.Article;

import java.util.List;

public interface IArticleService {
    // Retrieve an article by its ID
    ArticleResponseDto getArticleById(Long articleId);
    // Create a new article
    ArticleResponseDto createArticle(CreateArticleDto articleDto);
    // Update an existing article
    ArticleResponseDto updateArticle(Article updatedArticle, Long articleId);
    // Delete an article by its ID
    Long deleteArticleById(Long articleId);
    // Get all articles
    List<ArticleResponseDto> getAllArticles(int page);

    //  Get articles by Tags
    List<ArticleResponseDto> getArticlesByTag(String tag);

//    ...
}
