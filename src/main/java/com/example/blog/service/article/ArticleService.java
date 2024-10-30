package com.example.blog.service.article;

import com.example.blog.dto.article.ArticleFeedDto;
import com.example.blog.dto.article.ArticleResponseDto;
import com.example.blog.dto.article.ArticleDto;
import com.example.blog.dto.article.CreateArticleDto;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.model.Article;
import com.example.blog.model.Tag;
import com.example.blog.model.User;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.TagRepository;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
 import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
 import java.util.Set;
 import java.util.stream.Collectors;

import static com.example.blog.utils.Constants.PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @Override
    public ArticleResponseDto getArticleById(Long articleId) {
         Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article not found"));
             return modelMapper.map(article, ArticleResponseDto.class);
    }

    @Override
    public ArticleResponseDto createArticle(CreateArticleDto newArticle) {
//        get user
        User articleWrite = userRepository.findById(newArticle.getUserId()).map(user -> user).orElseThrow(() -> new ResourceNotFoundException("Write not found"));
 //        get tags
        Set<Tag> tags = new HashSet<>(tagRepository.findAllById( newArticle.getTagsIds()));
        Article article = new Article();
        article.setUser(articleWrite);
        article.setTags(tags);
        article.setTitle(newArticle.getTitle());
        article.setDescription(newArticle.getDescription());
//      save article
        Article createdArticle = articleRepository.save(article);
         return modelMapper.map(createdArticle, ArticleResponseDto.class);
    }

    @Override
    public ArticleResponseDto updateArticle(Article newArticle, Long articleId) {
        Article updatedArticle = articleRepository.findById(articleId).map(article -> {
//           fields to update
            article.setTitle(article.getTitle());
            article.setUpdatedAt(LocalDateTime.now());
            article.setDescription(article.getDescription());
            article.setTags(article.getTags());
            return articleRepository.save(article);
        }).orElseThrow(() -> new ResourceNotFoundException("Article not found"));

        return modelMapper.map(updatedArticle, ArticleResponseDto.class);
    }

    @Override
    public void deleteArticleById(Long articleId) {
          articleRepository.findById(articleId).map(article -> {
            articleRepository.deleteById(articleId);
            return articleId;
        }).orElseThrow(() -> new ResourceNotFoundException("Article not found"));
    }

    @Override
    public List<ArticleFeedDto> getAllArticles(int page) {
        // Set up pagination, with page size (e.g., 10 items per page)
        int pageSize = 10; // Adjust page size as needed
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Article> articlesFeed = articleRepository.findAll(pageable);
        if (!articlesFeed.isEmpty()){
            // Map each Article to ArticleResponseDto
            return articlesFeed
                    .getContent()
                    .stream()
                    .map(article -> modelMapper.map(article,ArticleFeedDto.class))
                    .collect(Collectors.toList());
        }else {
            throw new ResourceNotFoundException("No Articles found");
        }
    }

    @Override
    public List<ArticleResponseDto> getArticlesByTag(String tag) {
        // Set up pagination, with page size (e.g., 10 items per page)
        int pageSize = PAGE_SIZE; // Adjust page size as needed
//        Pageable pageable = PageRequest.of(page, pageSize);

        List<Article> articlesByTag = articleRepository.findByTagName(tag);

        if (!articlesByTag.isEmpty()){
            return  articlesByTag
                    .stream()
                    .map(article -> modelMapper.map(article, ArticleResponseDto.class))
                    .collect(Collectors.toList());
        }else {
            throw new ResourceNotFoundException("No Articles found");
        }
    }

}
