package com.example.blog.service.comment;

import com.example.blog.model.Comment;

public interface ICommentService {
    public Comment createComment(Comment comment);
    public void deleteCommentById(Long id);
}
