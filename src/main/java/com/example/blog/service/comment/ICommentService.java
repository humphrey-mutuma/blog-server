package com.example.blog.service.comment;

import com.example.blog.dto.comment.CommentDto;
import com.example.blog.dto.comment.CommentResDto;

public interface ICommentService {
//    create a new comment
    public CommentResDto createComment(CommentDto comment);
//  delete a comment
    public void deleteCommentById(Long id);
}
