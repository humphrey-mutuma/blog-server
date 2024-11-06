package com.example.blog.mapper;

import com.example.blog.post.dto.PostDto;
import com.example.blog.post.entity.Post;

public class ArticleMapper {

//    map article to dto
    public static PostDto mapToArticleDto(Post article){
        return new PostDto(
                        article.getId(),
                        article.getTitle(),
                        article.getDescription(),
                        article.getUser(),
                        article.getComments(),
                        article.getTags()
                );
    }

//    map dto to article
    public static Post mapToArticle(PostDto articleDto){
        return new Post(
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
