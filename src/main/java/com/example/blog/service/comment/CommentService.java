package com.example.blog.service.comment;

import com.example.blog.dto.comment.CreateCommentDto;
import com.example.blog.dto.comment.CreateCommentResDto;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.model.Article;
import com.example.blog.model.Comment;
import com.example.blog.model.User;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;

    @Override
    public CreateCommentResDto createComment(CreateCommentDto commentData) {
        // Fetch the user who wrote the comment
        User commentWriter = userRepository.findById(commentData.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Writer not found"));

        // Fetch the article to which the comment is associated
        Article commentArticle = articleRepository.findById(commentData.getArticleId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment article not found"));

        // Create and set up a new Comment entity
        Comment comment = new Comment();
        comment.setUser(commentWriter);
        comment.setArticle(commentArticle);
        comment.setTitle(commentData.getTitle());

        // Save the new comment in the repository
        Comment newComment = commentRepository.save(comment);

        // Map and return the new comment as a CreateCommentDto
        return modelMapper.map(newComment, CreateCommentResDto.class);
    }

    @Override
    public Long deleteCommentById(Long commentId) {
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.deleteById(commentId);
            return commentId;
        }).orElseThrow(()-> new ResourceNotFoundException("Comment not found!"));
    }
}
