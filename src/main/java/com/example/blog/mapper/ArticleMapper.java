package com.example.blog.mapper;

import com.example.blog.dto.article.ArticleDto;
import com.example.blog.model.Article;

import java.util.ArrayList;

public class ArticleMapper {

//    map article to dto
    public static ArticleDto mapToArticleDto(Article article){
        return new ArticleDto(
                        article.getId(),
                        article.getTitle(),
                        article.getDescription(),
                        article.getUser(),
                        article.getComments(),
                        article.getTags()
                );
    }

//    map dto to article
    public static Article mapToArticle(ArticleDto articleDto){
        return new Article(
                        articleDto.getId(),
                        articleDto.getTitle(),
                        articleDto.getDescription(),
                        articleDto.getUser().getCreatedAt(),
                        articleDto.getUser().getUpdatedAt(),
                        articleDto.getUser(),
                        articleDto.getComments(),
                        articleDto.getTags()
                );
    }
}
