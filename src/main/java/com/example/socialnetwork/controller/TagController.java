package com.example.socialnetwork.controller;

import com.example.socialnetwork.domain.dto.TagDto;
import com.example.socialnetwork.domain.model.Tag;
import com.example.socialnetwork.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagRepository tagRepository;

    @GetMapping
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new TagDto(tag.getId(), tag.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tag not found"));
        return new TagDto(tag.getId(), tag.getName());
    }

    @PostMapping
    public TagDto createTag(@RequestBody TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        Tag saved = tagRepository.save(tag);
        return new TagDto(saved.getId(), saved.getName());
    }

    @PutMapping("/{id}")
    public TagDto updateTag(@PathVariable Long id, @RequestBody TagDto tagDto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tag not found"));
        tag.setName(tagDto.getName());
        Tag saved = tagRepository.save(tag);
        return new TagDto(saved.getId(), saved.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        if (!tagRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Tag not found");
        }
        tagRepository.deleteById(id);
    }
}
