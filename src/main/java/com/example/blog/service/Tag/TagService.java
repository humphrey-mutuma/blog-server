package com.example.blog.service.Tag;

import com.example.blog.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    @Override
    public Tag createTag(Tag tag) {
        return null;
    }

    @Override
    public void deleteTagById(Long id) {

    }

    @Override
    public Set<Tag> getPaginatedTags() {
        return Set.of();
    }

}
