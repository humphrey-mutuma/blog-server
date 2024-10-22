package com.example.blog.repository;

import com.example.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {


    // Custom query to count the number of tags for a specific article
    @Query("SELECT COUNT(t) FROM Article a JOIN a.tags t WHERE a.id = :articleId")
    int articleTagsCount(@Param("articleId") Long articleId);

}
