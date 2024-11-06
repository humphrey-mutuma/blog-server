package com.example.blog.comment;
import com.example.blog.comment.dto.CommentDto;
import com.example.blog.comment.dto.CommentResDto;
import com.example.blog.response.ApiResponse;
import com.example.blog.comment.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/comments")
public class CommentController {
    private final CommentService commentService;

//
    @PostMapping
    public ResponseEntity<ApiResponse<CommentResDto>>  createComment(@RequestBody CommentDto commentData){
        CommentResDto newComment = commentService.createComment(commentData);
        if (newComment != null){
            return ResponseEntity.ok(new ApiResponse<>("", newComment));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<?>> deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentById( commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>("Comment deleted successfully", true));
    }
}
