package com.example.blog.service.article;

import com.example.blog.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {
    @Override
    public Article getArticleById(Long articleId) {
        return null;
    }

    @Override
    public Article createArticle(Article article) {
        return null;
    }

    @Override
    public Article updateArticle(Article updatedArticle) {
        return null;
    }

    @Override
    public Long deleteArticleById(Long articleId) {

    }

    @Override
    public List<Article> getAllArticles() {
        return List.of();
    }

    @Override
    public List<Article> getArticlesByTag(String tag) {
        return List.of();
    }

}
