package com.example.blog.controller;

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
    public ResponseEntity<Article> getArticleById(@PathVariable Long articleId) {
         Article article = articleService.getArticleById(articleId);
         if (article != null ){
             return ResponseEntity.ok(article);
         }else {
             return ResponseEntity.notFound().build();
         }
     }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article newArticle = articleService.createArticle(article);
        if (newArticle != null){
             return    ResponseEntity.status(HttpStatus.CREATED).body(newArticle);
        }else {
             return ResponseEntity.badRequest().build();
        }
     }

    @PutMapping("/{articleId}")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article, @PathVariable Long articleId) {
        Article updatedArticle = articleService.updateArticle(article, articleId);
        if (updatedArticle != null){
            return ResponseEntity.ok(updatedArticle);
        }else {
            return ResponseEntity.badRequest().build();
        }
     }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<ApiResponse> deleteArticleById(@PathVariable Long articleId) {
            Long deletedArticleId = articleService.deleteArticleById(articleId);
            if (deletedArticleId != null) {
                return ResponseEntity.ok(new ApiResponse("Successfully deleted!", deletedArticleId));
            }else {
                return ResponseEntity.badRequest().build();
            }
    }

    @GetMapping("/")
    public ResponseEntity<List<Article>> getAllArticles(@RequestParam int page) {
        List<Article> articles = articleService.getAllArticles(page);
        if (articles != null && !articles.isEmpty()){
            return ResponseEntity.ok(articles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Article>> getArticlesByTag(@RequestParam String tag) {
        List<Article> articlesByTag = articleService.getArticlesByTag(tag);
        if (articlesByTag != null && !articlesByTag.isEmpty()){
            return ResponseEntity.ok(articlesByTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
