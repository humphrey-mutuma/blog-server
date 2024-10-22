package com.example.blog.service.comment;

import com.example.blog.model.Comment;

public interface ICommentService {
//    create a new comment
    public Comment createComment(Comment comment);
//  delete a comment
    public void deleteCommentById(Long id);
}
