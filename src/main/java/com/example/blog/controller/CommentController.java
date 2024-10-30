package com.example.blog.controller;
import com.example.blog.dto.comment.CommentDto;
import com.example.blog.dto.comment.CommentResDto;
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
    public ResponseEntity<CommentResDto>  createComment(@RequestBody CommentDto commentData){
        CommentResDto newComment = commentService.createComment(commentData);
        if (newComment != null){
            return ResponseEntity.ok(newComment);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentById( commentId);
        return ResponseEntity.ok(new ApiResponse("Comment deleted successfully", true));
    }
}
