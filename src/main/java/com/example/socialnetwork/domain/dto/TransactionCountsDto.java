package com.example.socialnetwork.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCountsDto {
    private long users;
    private long posts;
    private long comments;
}
