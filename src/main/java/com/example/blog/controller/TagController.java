package com.example.blog.controller;

import com.example.blog.model.Tag;
import com.example.blog.response.ApiResponse;
import com.example.blog.service.Tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/tags")
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        Tag newtag = tagService.createTag(tag);
        if (newtag != null){
            return ResponseEntity.ok(newtag);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable Long tagId){
         tagService.deleteTagById(tagId);
         return  ResponseEntity.ok(new ApiResponse("Tag deleted successfully!", true));
    }
}