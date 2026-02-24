package com.example.socialnetwork.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
}
