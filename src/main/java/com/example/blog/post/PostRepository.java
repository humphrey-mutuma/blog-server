package com.example.blog.post;

import com.example.blog.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Custom query to count the number of tags for a specific post
    @Query("SELECT COUNT(t) FROM Post a JOIN a.tags t WHERE a.id = :postId")
    int postTagsCount(@Param("postId") Long postId);

    //    fetch posts by tag
    @Query("SELECT a FROM Post a JOIN a.tags t WHERE t.name = :tag")
    List<Post> findByTagName(@Param("tag") String tag);


}
