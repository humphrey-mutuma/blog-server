package com.example.blog.comment.services;

import com.example.blog.comment.dto.CommentDto;
import com.example.blog.comment.dto.CommentResDto;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.post.entity.Post;
import com.example.blog.comment.entity.Comment;
import com.example.blog.user.entity.User;
import com.example.blog.post.PostRepository;
import com.example.blog.comment.CommentRepository;
import com.example.blog.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Override
    public CommentResDto createComment(CommentDto commentData) {
        // Fetch the user who wrote the comment
        User commentWriter = userRepository.findById(commentData.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Writer not found"));

        // Fetch the post to which the comment is associated
        Post commentPost = postRepository.findById(commentData.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment post not found"));

        // Create and set up a new Comment entity
        Comment comment = new Comment();
        comment.setUser(commentWriter);
        comment.setPost(commentPost);
        comment.setTitle(commentData.getTitle());

        // Save the new comment in the repository
        Comment newComment = commentRepository.save(comment);

        // Map and return the new comment as a CreateCommentDto
        return modelMapper.map(newComment, CommentResDto.class);
    }

    @Override
    public void deleteCommentById(Long commentId) {
          commentRepository.findById(commentId).map(comment -> {
            commentRepository.deleteById(commentId);
            return commentId;
        }).orElseThrow(()-> new ResourceNotFoundException("Comment not found!"));
    }
}
