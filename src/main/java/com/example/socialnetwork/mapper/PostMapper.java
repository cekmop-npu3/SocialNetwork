package com.example.socialnetwork.mapper;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.domain.model.Post;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PostMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public PostDto toDto(Post post) {
        if (post == null) {
            return null;
        }
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getUser() != null ? post.getUser().getUsername() : null);
        dto.setCreatedAt(post.getCreatedAt() != null ? post.getCreatedAt().format(FORMATTER) : null);
        return dto;
    }

    public Post toEntity(PostDto dto) {
        if (dto == null) {
            return null;
        }
        Post post = new Post();
        post.setId(dto.getId());
        post.setContent(dto.getContent());
        post.setCreatedAt(dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt(), FORMATTER) : null);
        return post;
    }
}