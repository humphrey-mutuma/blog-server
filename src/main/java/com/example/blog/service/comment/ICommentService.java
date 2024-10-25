package com.example.blog.service.comment;

import com.example.blog.dto.comment.CreateCommentDto;
import com.example.blog.dto.comment.CreateCommentResDto;
import com.example.blog.model.Comment;

public interface ICommentService {
//    create a new comment
    public CreateCommentResDto createComment(CreateCommentDto comment);
//  delete a comment
    public Long deleteCommentById(Long id);
}
