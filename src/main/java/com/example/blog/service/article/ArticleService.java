package com.example.blog.service.article;

import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.model.Article;
import com.example.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Override
    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article not found") );
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article updatedArticle, Long articleId) {
       return articleRepository.findById(articleId).map(article -> {
//           fields to update
           article.setTitle(article.getTitle());
           article.setUpdatedAt(LocalDateTime.now());
           article.setDescription(article.getDescription());
           article.setTags(article.getTags());
           return articleRepository.save(article);
       }).orElseThrow(() -> new ResourceNotFoundException("Article not found"));

    }

    @Override
    public Long deleteArticleById(Long articleId) {
        return articleRepository.findById(articleId).map(article -> {
            articleRepository.deleteById(articleId);
            return articleId;
        }).orElseThrow(() -> new ResourceNotFoundException("Article not found"));
    }

    @Override
    public List<Article> getAllArticles(int page) {
//        paginate
        List<Article> articlesFeed = articleRepository.findAll();
        if (!articlesFeed.isEmpty()){
            return articlesFeed;
        }else {
            throw new ResourceNotFoundException("No Articles found");
        }
    }

    @Override
    public List<Article> getArticlesByTag(String tag) {
//        paginate
        List<Article> articlesByTag = articleRepository.findByTagName(tag);
        if (!articlesByTag.isEmpty()){
            return articlesByTag;
        }else {
            throw new ResourceNotFoundException("No Articles found");
        }
    }

}
