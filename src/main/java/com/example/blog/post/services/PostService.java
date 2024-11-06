package com.example.blog.post.services;

import com.example.blog.user.entity.User;
import com.example.blog.post.dto.PostFeedDto;
import com.example.blog.post.dto.PostResponseDto;
import com.example.blog.post.dto.CreatePostDto;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.post.entity.Post;
import com.example.blog.Tag.entity.Tag;
 import com.example.blog.post.PostRepository;
import com.example.blog.Tag.TagRepository;
import com.example.blog.user.UserRepository;
import lombok.RequiredArgsConstructor;
 import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
 import java.util.Set;
 import java.util.stream.Collectors;

import static com.example.blog.utils.Constants.PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostResponseDto getPostById(Long postId) {
         Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
             return modelMapper.map(post, PostResponseDto.class);
    }

    @Override
    public PostResponseDto createPost(CreatePostDto newPost) {
//        get user
        User postWrite = userRepository.findById(newPost.getUserId()).map(user -> user).orElseThrow(() -> new ResourceNotFoundException("Write not found"));
 //        get tags
        Set<Tag> tags = new HashSet<>(tagRepository.findAllById( newPost.getTagsIds()));
        Post post = new Post();
        post.setUser(postWrite);
        post.setTags(tags);
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
//      save post
        Post createdPost = postRepository.save(post);
         return modelMapper.map(createdPost, PostResponseDto.class);
    }

    @Override
    public PostResponseDto updatePost(Post newPost, Long postId) {
        Post updatedPost = postRepository.findById(postId).map(post -> {
//           fields to update
            post.setTitle(post.getTitle());
            post.setUpdatedAt(LocalDateTime.now());
            post.setDescription(post.getDescription());
            post.setTags(post.getTags());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        return modelMapper.map(updatedPost, PostResponseDto.class);
    }

    @Override
    public void deletePostById(Long postId) {
          postRepository.findById(postId).map(post -> {
            postRepository.deleteById(postId);
            return postId;
        }).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }

    @Override
    public List<PostFeedDto> getAllPosts(int page) {
        // Set up pagination, with page size (e.g., 10 items per page)
        int pageSize = 10; // Adjust page size as needed
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Post> postsFeed = postRepository.findAll(pageable);
        if (!postsFeed.isEmpty()){
            // Map each Post to PostResponseDto
            return postsFeed
                    .getContent()
                    .stream()
                    .map(post -> modelMapper.map(post, PostFeedDto.class))
                    .collect(Collectors.toList());
        }else {
            throw new ResourceNotFoundException("No Posts found");
        }
    }

    @Override
    public List<PostResponseDto> getPostsByTag(String tag) {
        // Set up pagination, with page size (e.g., 10 items per page)
        int pageSize = PAGE_SIZE; // Adjust page size as needed
//        Pageable pageable = PageRequest.of(page, pageSize);

        List<Post> postsByTag = postRepository.findByTagName(tag);

        if (!postsByTag.isEmpty()){
            return  postsByTag
                    .stream()
                    .map(post -> modelMapper.map(post, PostResponseDto.class))
                    .collect(Collectors.toList());
        }else {
            throw new ResourceNotFoundException("No Posts found");
        }
    }

}
