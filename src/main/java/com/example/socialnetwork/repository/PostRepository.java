package com.example.socialnetwork.repository;

import com.example.socialnetwork.domain.model.Post;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final Map<Long, Post> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Post save(Post post) {
        if (post.getId() == null) {
            post.setId(idGenerator.getAndIncrement());
            post.setCreatedAt(LocalDateTime.now());
        }
        storage.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id) {
        return storage.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<Post> findByAuthor(String author) {
        return storage.values().stream()
                .filter(post -> post.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
