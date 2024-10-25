package com.example.blog.service.Tag;

import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.model.Tag;
import com.example.blog.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    private final TagRepository tagRepository;

    @Override
    public Tag createTag(Tag tag) {
        Tag newTag = new Tag();
        newTag.setName(tag.getName());
        return  tagRepository.save(newTag);
    }

    @Override
    public Long deleteTagById(Long id) {
      return tagRepository.findById(id).map(tag -> {
          tagRepository.deleteById(id);
          return id;
      }).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
    }

    @Override
    public Set<Tag> getPaginatedTags() {
        return Set.of();
    }

}
