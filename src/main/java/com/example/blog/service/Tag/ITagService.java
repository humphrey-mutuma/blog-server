package com.example.blog.service.Tag;

import com.example.blog.model.Tag;

import java.util.Set;

public interface ITagService {
    public Tag createTag(Tag tag);
    public void deleteTagById(Long id);
    public  Set<Tag> getPaginatedTags() ;
    public Long getTagsCount();
}
