package com.example.blog.post;

import com.example.blog.post.dto.PostFeedDto;
import com.example.blog.post.dto.PostResponseDto;
import com.example.blog.post.dto.CreatePostDto;
import com.example.blog.post.entity.Post;
import com.example.blog.response.ApiResponse;
import com.example.blog.post.services.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/posts")
public class PostController {
    private final IPostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPostById(@PathVariable Long postId) {
        PostResponseDto post = postService.getPostById(postId);

        return ResponseEntity.ok(new ApiResponse<>("", post));
     }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(@RequestBody CreatePostDto post) {
        PostResponseDto newPost = postService.createPost(post);
        return  ResponseEntity
                      .status(HttpStatus.CREATED)
                      .body(new ApiResponse<>("", newPost));
     }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(@RequestBody Post post, @PathVariable Long postId) {
        PostResponseDto updatedPost = postService.updatePost(post, postId);
             return ResponseEntity
                     .ok(new ApiResponse<>("", updatedPost));
     }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePostById(@PathVariable Long postId) {
         postService.deletePostById(postId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>("Successfully deleted!", true));
    }

    @GetMapping("/feed")
    public ResponseEntity<ApiResponse<List<PostFeedDto>>> getAllPosts(@RequestParam int page) {
        List<PostFeedDto> posts = postService.getAllPosts(page);
             return ResponseEntity.ok(new ApiResponse<>("", posts));
    }

    @GetMapping("/tags")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByTag(@RequestParam String tag) {
        List<PostResponseDto> postsByTag = postService.getPostsByTag(tag);
             return ResponseEntity.ok(new ApiResponse<>("", postsByTag));

    }

}
