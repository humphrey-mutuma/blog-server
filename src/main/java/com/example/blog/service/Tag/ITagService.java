package com.example.blog.service.Tag;

import com.example.blog.model.Tag;

import java.util.Set;

public interface ITagService {
//    create a tag
    Tag createTag(Tag tag);
//   delete a tag
    Long deleteTagById(Long id);
//   fetch top used tags based on the number of articles tagged on
    Set<Tag> getPaginatedTags() ;
//    ....
}
