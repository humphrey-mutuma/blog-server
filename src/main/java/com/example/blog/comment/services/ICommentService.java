package com.example.blog.comment.services;

import com.example.blog.comment.dto.CommentDto;
import com.example.blog.comment.dto.CommentResDto;

public interface ICommentService {
//    create a new comment
    public CommentResDto createComment(CommentDto comment);
//  delete a comment
    public void deleteCommentById(Long id);
}
