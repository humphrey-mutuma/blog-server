package com.example.blog.Tag.services;

import com.example.blog.Tag.entity.Tag;

import java.util.Set;

public interface ITagService {
//    create a tag
    Tag createTag(Tag tag);
//   delete a tag
    void deleteTagById(Long id);
//   fetch top used tags based on the number of posts tagged on
    Set<Tag> getPaginatedTags() ;
//    ....
}
