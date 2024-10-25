package com.example.blog.controller;
import com.example.blog.dto.comment.CreateCommentDto;
import com.example.blog.dto.comment.CreateCommentResDto;
import com.example.blog.model.Article;
import com.example.blog.response.ApiResponse;
import com.example.blog.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResDto>  createComment(@RequestBody CreateCommentDto commentData){
        CreateCommentResDto newComment = commentService.createComment(commentData);
        if (newComment != null){
            return ResponseEntity.ok(newComment);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        Long deletedComment = commentService.deleteCommentById( commentId);
        if (deletedComment != null){
            return ResponseEntity.ok(new ApiResponse("Comment deleted successfully", deletedComment));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
