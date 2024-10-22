package com.example.blog.service.user;

import com.example.blog.model.Article;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User updateUser(User user, Long userId) {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean bookmarkArticle(Long articleId) {
        return false;
    }

    @Override
    public List<Long> getUserBookmarks(Long userid) {
        return List.of();
    }
}
