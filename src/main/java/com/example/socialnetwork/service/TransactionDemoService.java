package com.example.socialnetwork.service;

import com.example.socialnetwork.domain.dto.TransactionCountsDto;
import com.example.socialnetwork.domain.model.Post;
import com.example.socialnetwork.domain.model.User;
import com.example.socialnetwork.repository.CommentRepository;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionDemoService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public TransactionCountsDto getCounts() {
        return new TransactionCountsDto(
                userRepository.count(),
                postRepository.count(),
                commentRepository.count()
        );
    }

    public void saveRelatedEntitiesWithoutTransaction(String prefix) {
        String suffix = "_" + System.nanoTime();
        User user = createUser(prefix + "_no_tx" + suffix);
        createPost(prefix + " post without tx", user);
        throw new IllegalStateException("Artificial error without @Transactional");
    }

    @Transactional
    public void saveRelatedEntitiesWithTransaction(String prefix) {
        String suffix = "_" + System.nanoTime();
        User user = createUser(prefix + "_with_tx" + suffix);
        createPost(prefix + " post with tx", user);
        throw new IllegalStateException("Artificial error with @Transactional");
    }

    private User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(username + "@mail.local");
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    private Post createPost(String content, User user) {
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
}
