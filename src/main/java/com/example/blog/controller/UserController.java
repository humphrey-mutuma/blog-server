package com.example.blog.controller;

import com.example.blog.dto.user.UserDto;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.model.User;
import com.example.blog.response.ApiResponse;
import com.example.blog.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<UserDto> userOptional = userService.getUserByEmail(email);
        if (userOptional.isPresent()){
              return ResponseEntity.ok(userOptional.get());
         }else{
            throw new ResourceNotFoundException("No user found with email: " + email);
     }

    }
//    update a user
    @PostMapping("/${userId}")
    public ResponseEntity<UserDto> updateUser(User user, Long userId) {
        UserDto optionalUser = userService.updateUser(user,userId);
            return ResponseEntity.ok(optionalUser);

     }

    @DeleteMapping("/${userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long userId) {
        try {
            boolean wasDeleted = userService.deleteUserById(userId);
           return   ResponseEntity.ok(new ApiResponse("User deleted successfully",wasDeleted));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.ok(new ApiResponse("Delete user failed!",false));
        }

     }

    @PutMapping("/bookmarks/${userid}/${articleId}")
    public ResponseEntity<ApiResponse> bookmarkArticle(@PathVariable Long userId , @PathVariable Long articleId) {
       return null;
    }

    @GetMapping("/bookmarks/${userId}")
    public ResponseEntity<ApiResponse> getUserBookmarks(@PathVariable Long userId) {
        try {
            List<Long> bookmarks = userService.getUserBookmarks(userId);
            return ResponseEntity.ok(new ApiResponse("User Bookmarks success!", bookmarks));

        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("Not bookmarks found", e.getMessage()));
        }
    }
}
