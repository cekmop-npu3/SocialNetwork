package com.example.socialnetwork.service.impl;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.domain.model.Post;
import com.example.socialnetwork.domain.model.Tag;
import com.example.socialnetwork.domain.model.User;
import com.example.socialnetwork.mapper.PostMapper;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.TagRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.service.PostService;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional(readOnly = true)
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post not found"));
        return postMapper.toDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostsByAuthor(String author) {
        return postRepository.findByUserUsername(author).stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPostsNPlusOne() {
        return postRepository.findAllForNPlusOne().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPostsEntityGraph() {
        return postRepository.findAllWithEntityGraph().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPostsFetchJoin() {
        return postRepository.findAllWithFetchJoin().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public PostDto createPost(PostDto postDto) {
        User user = findOrCreateUser(postDto.getAuthor());
        Post post = postMapper.toEntity(postDto);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        post.setTags(resolveTags(postDto.getTags()));
        Post saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post not found"));
        post.setContent(postDto.getContent());
        if (postDto.getAuthor() != null && !postDto.getAuthor().isBlank()) {
            post.setUser(findOrCreateUser(postDto.getAuthor()));
        }
        post.setTags(resolveTags(postDto.getTags()));
        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Post not found");
        }
        postRepository.deleteById(id);
    }

    private User findOrCreateUser(String username) {
        if (username == null || username.isBlank()) {
            throw new ResponseStatusException(BAD_REQUEST, "Author is required");
        }
        return userRepository.findByUsername(username)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(username);
                    newUser.setEmail(username + "@mail.local");
                    newUser.setCreatedAt(LocalDateTime.now());
                    return userRepository.save(newUser);
                });
    }

    private Set<Tag> resolveTags(List<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return new LinkedHashSet<>();
        }
        Set<Tag> tags = new LinkedHashSet<>();
        for (String tagName : tagNames) {
            tags.add(tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(new Tag(null, tagName, new LinkedHashSet<>()))));
        }
        return tags;
    }
}
