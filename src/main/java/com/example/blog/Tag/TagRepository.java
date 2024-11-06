package com.example.blog.Tag;
import com.example.blog.Tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    // Custom query to count the number of posts attached to a tag
    @Query("SELECT COUNT(a) FROM Post a JOIN a.tags t WHERE t.id = :tagId")
    Long countPostsByTagId(@Param("tagId") Long tagId);
}
