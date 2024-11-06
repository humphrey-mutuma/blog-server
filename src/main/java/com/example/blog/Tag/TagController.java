package com.example.blog.Tag;

import com.example.blog.Tag.entity.Tag;
import com.example.blog.response.ApiResponse;
import com.example.blog.Tag.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/tags")
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<ApiResponse<Tag>> createTag(@RequestBody Tag tag){
        Tag newtag = tagService.createTag(tag);
             return ResponseEntity.ok(new ApiResponse<>("" , newtag));
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<ApiResponse<?>> deleteTag(@PathVariable Long tagId){
         tagService.deleteTagById(tagId);
         return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>("Tag deleted successfully!", true));
    }
}