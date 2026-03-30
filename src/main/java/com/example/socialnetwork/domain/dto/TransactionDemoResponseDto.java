package com.example.socialnetwork.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDemoResponseDto {
    private String scenario;
    private String errorMessage;
    private TransactionCountsDto before;
    private TransactionCountsDto after;
}
