package com.example.blog.controller;

import com.example.blog.dto.user.ArticlesCountDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.model.Article;
import com.example.blog.model.User;
import com.example.blog.response.ApiResponse;
import com.example.blog.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;


    // Fetch user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<UserDto> userOptional = userService.getUserByEmail(email);
         return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
//    update a user
    @PostMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user, @PathVariable Long userId) {
        UserDto optionalUser = userService.updateUser(user, userId);
        return optionalUser != null ? ResponseEntity.ok(optionalUser) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return   ResponseEntity.ok(new ApiResponse("User deleted successfully", true));
    }

    @PutMapping("/bookmarks/{userId}/{articleId}")
    public ResponseEntity<ApiResponse> bookmarkArticle(@PathVariable Long userId , @PathVariable Long articleId) {
       return null;
    }

    @GetMapping("/bookmarks/{userId}")
    public ResponseEntity<List<Article>> getUserBookmarks(@PathVariable Long userId) {
        List<Article> bookmarks = userService.getUserBookmarks(userId);
        if (bookmarks != null && !bookmarks.isEmpty()) {
            return ResponseEntity.ok(bookmarks);
        } else {
            return ResponseEntity.notFound().build();
        }
     }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Article>> getUserArticles(@PathVariable Long userId) {
        List<Article> userArticles = userService.getUserArticles(userId);
        if (userArticles != null && !userArticles.isEmpty()) {
            return ResponseEntity.ok(userArticles); // Return 200 OK with the list of articles
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if no articles found
        }
    }

    @GetMapping("/articles/{userId}")
    public ResponseEntity<ArticlesCountDto> getUsersArticlesCount(Long userId) {
        int userArticlesCount = userService.getUsersArticlesCount(userId);
        ArticlesCountDto countDto = new ArticlesCountDto(userArticlesCount);
        return ResponseEntity.ok(countDto);
    }
}
