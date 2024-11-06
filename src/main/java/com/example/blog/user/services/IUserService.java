package com.example.blog.user.services;

import com.example.blog.user.entity.User;
import com.example.blog.user.dto.UserDto;
import com.example.blog.post.entity.Post;
 
import java.util.List;

public interface IUserService {
  //    update user details
    UserDto updateUser(User user, Long userId);
//    delete user
    String   deleteUserById(Long userId);
//    bookmark an post
    boolean bookmarkPost(Long postId);

    boolean bookmarkPost(Long userId, Long postId);

    //    get user bookmarks
    List<Post> getUserBookmarks(Long userid);
    //    get a users posts
    List<Post> getUserPosts(Long userId);
    //    get users posts count
    int getUsersPostsCount(Long userId);
//
}
