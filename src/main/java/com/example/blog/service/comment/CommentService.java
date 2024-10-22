package com.example.blog.service.comment;

import com.example.blog.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    @Override
    public Comment createComment(Comment comment) {
        return null;
    }

    @Override
    public void deleteCommentById(Long id) {

    }
}
