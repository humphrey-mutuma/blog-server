package com.example.blog.user;

import com.example.blog.user.dto.PostsCountDto;
import com.example.blog.user.dto.UserDto;
import com.example.blog.post.entity.Post;
 import com.example.blog.response.ApiResponse;
import com.example.blog.user.entity.User;
import com.example.blog.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;


//    update a user
    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody User user, @PathVariable Long userId) {
        UserDto optionalUser = userService.updateUser(user, userId);
        return   ResponseEntity.ok(new ApiResponse<>("", optionalUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> deleteUserById(@PathVariable Long userId) {

        return   ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>("User deleted successfully", userService.deleteUserById(userId)));
    }

    @PutMapping("/bookmarks/{userId}/{articleId}")
    public ResponseEntity<ApiResponse<?>> bookmarkPost(@PathVariable Long userId , @PathVariable Long articleId) {
       return  ResponseEntity
               .status(HttpStatus.NO_CONTENT)
               .body(new ApiResponse<>("User deleted successfully",  userService.bookmarkPost(userId)));
    }

    @GetMapping("/bookmarks/{userId}")
    public ResponseEntity<ApiResponse<List<Post>>> getUserBookmarks(@PathVariable Long userId) {
        List<Post> bookmarks = userService.getUserBookmarks(userId);
             return ResponseEntity
                     .ok(new ApiResponse<>("", bookmarks));

     }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<Post>>> getUserPosts(@PathVariable Long userId) {
        List<Post> userPosts = userService.getUserPosts(userId);
             return ResponseEntity
                     .ok(new ApiResponse<>("", userPosts)); // Return 200 OK with the list of articles
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<ApiResponse<PostsCountDto>> getUsersPostsCount(Long userId) {
        int userPostsCount = userService.getUsersPostsCount(userId);
        PostsCountDto countDto = new PostsCountDto(userPostsCount);
        return ResponseEntity.ok(new ApiResponse<>("", countDto));
    }
}
