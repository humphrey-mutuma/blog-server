package com.example.blog.service.user;

import com.example.blog.dto.user.UserDto;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.model.Article;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        return Optional.of(modelMapper.map(user, UserDto.class));
    }


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
    public Long deleteUserById(Long userId) {
         return userRepository.findById(userId).map(user -> {
             userRepository.deleteById(userId);
             return userId;
         }).orElseThrow(() -> new ResourceNotFoundException("User not found with ID " + userId));
    }

    @Override
    public boolean bookmarkArticle(Long articleId) {
        return false;
    }


    @Override
    public boolean bookmarkArticle(Long userId, Long articleId) {
         return userRepository.findById(userId).map(user -> {
            //    you may wish to check if the article exists first
             user.getBookmarks().add(articleId);
             userRepository.save(user);
           return true;
         }).orElseThrow(() -> new ResourceNotFoundException("Bookmark failed"));
    }

    @Override
    public List<Article> getUserBookmarks(Long userId) {
        return List.of();
    }

    @Override
    public List<Article> getUserArticles(Long userId) {
        return List.of();
    }

    @Override
    public int getUsersArticlesCount(Long userId) {
        return 0;
    }
}
