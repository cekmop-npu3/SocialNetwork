package com.example.socialnetwork.mapper;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.domain.model.Post;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public PostDto toDto(Post post) {
        if (post == null) {
            return null;
        }
        final List<String> tagNames = post.getTags().stream()
                .map(tag -> tag.getName())
                .toList();
        return new PostDto(
                post.getId(),
                post.getContent(),
                post.getUser() == null ? null : post.getUser().getUsername(),
                post.getCreatedAt() == null ? null : post.getCreatedAt().format(FORMATTER),
                post.getComments() == null ? 0 : post.getComments().size(),
                tagNames
        );
    }

    public Post toEntity(PostDto dto) {
        if (dto == null) {
            return null;
        }
        Post post = new Post();
        post.setId(dto.getId());
        post.setContent(dto.getContent());
        if (dto.getCreatedAt() != null) {
            post.setCreatedAt(LocalDateTime.parse(dto.getCreatedAt(), FORMATTER));
        }
        return post;
    }
}
