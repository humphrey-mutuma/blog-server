package com.example.blog.user.services;

import com.example.blog.user.entity.User;
import com.example.blog.user.dto.UserDto;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.post.entity.Post;
 import com.example.blog.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto updateUser(User user, Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        // Update only the necessary fields
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getBookmarks() != null) {
            existingUser.setBookmarks(user.getBookmarks());
        }
        // Save the updated user entity
        User updatedUser = userRepository.save(existingUser);

        // Convert the updated entity to DTO
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public String deleteUserById(Long userId) {
      return   userRepository.findById(userId).map(user -> {
             userRepository.deleteById(userId);
             return "User deleted successfully";
         }).orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + userId));
    }

    @Override
    public boolean bookmarkPost(Long postId) {
        return false;
    }


    @Override
    public boolean bookmarkPost(Long userId, Long postId) {
         return userRepository.findById(userId).map(user -> {
            //    you may wish to check if the article exists first
             user.getBookmarks().add(postId);
             userRepository.save(user);
           return true;
         }).orElseThrow(() -> new ResourceNotFoundException("Bookmark failed"));
    }

    @Override
    public List<Post> getUserBookmarks(Long userId) {
        return List.of();
    }

    @Override
    public List<Post> getUserPosts(Long userId) {
        return List.of();
    }

    @Override
    public int getUsersPostsCount(Long userId) {
        return 0;
    }
}
