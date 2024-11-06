package com.example.blog.post.services;

import com.example.blog.post.dto.PostFeedDto;
import com.example.blog.post.dto.PostResponseDto;
import com.example.blog.post.dto.CreatePostDto;
import com.example.blog.post.entity.Post;

import java.util.List;

public interface IPostService {
    // Retrieve post by ID
    PostResponseDto getPostById(Long postId);
    // Create a new post
    PostResponseDto createPost(CreatePostDto postDto);
    // Update an existing post
    PostResponseDto updatePost(Post updatedPost, Long postId);
    // Delete an post by its ID
    void deletePostById(Long postId);
    // Get all posts
    List<PostFeedDto> getAllPosts(int page);

    //  Get posts by Tags
    List<PostResponseDto> getPostsByTag(String tag);

//    ...
}
