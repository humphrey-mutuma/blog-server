package com.example.blog.controller;

import com.example.blog.dto.article.ArticleFeedDto;
import com.example.blog.dto.article.ArticleResponseDto;
import com.example.blog.dto.article.ArticleDto;
import com.example.blog.dto.article.CreateArticleDto;
import com.example.blog.model.Article;
import com.example.blog.response.ApiResponse;
import com.example.blog.service.article.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/articles")
public class ArticleController {
    private final IArticleService articleService;

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long articleId) {
        ArticleResponseDto article = articleService.getArticleById(articleId);
         if (article != null ){
             return ResponseEntity.ok(article);
         }else {
             return ResponseEntity.notFound().build();
         }
     }

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody CreateArticleDto article) {
        ArticleResponseDto newArticle = articleService.createArticle(article);
        if (newArticle != null){
             return    ResponseEntity.status(HttpStatus.CREATED).body(newArticle);
        }else {
             return ResponseEntity.badRequest().build();
        }
     }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> updateArticle(@RequestBody Article article, @PathVariable Long articleId) {
        ArticleResponseDto updatedArticle = articleService.updateArticle(article, articleId);
        if (updatedArticle != null){
            return ResponseEntity.ok(updatedArticle);
        }else {
            return ResponseEntity.badRequest().build();
        }
     }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<ApiResponse> deleteArticleById(@PathVariable Long articleId) {
         articleService.deleteArticleById(articleId);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted!", true));
    }

    @GetMapping("/feed")
    public ResponseEntity<List<ArticleFeedDto>> getAllArticles(@RequestParam int page) {
        List<ArticleFeedDto> articles = articleService.getAllArticles(page);
        if (articles != null && !articles.isEmpty()){
            return ResponseEntity.ok(articles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<List<ArticleResponseDto>> getArticlesByTag(@RequestParam String tag) {
        List<ArticleResponseDto> articlesByTag = articleService.getArticlesByTag(tag);
        if (articlesByTag != null && !articlesByTag.isEmpty()){
            return ResponseEntity.ok(articlesByTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
