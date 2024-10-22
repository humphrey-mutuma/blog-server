package com.example.blog.repository;
import com.example.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    // Custom query to count the number of articles attached to a tag
    @Query("SELECT COUNT(a) FROM Article a JOIN a.tags t WHERE t.id = :tagId")
    Long countArticlesByTagId(@Param("tagId") Long tagId);
}
