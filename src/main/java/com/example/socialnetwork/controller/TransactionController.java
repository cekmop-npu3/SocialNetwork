package com.example.socialnetwork.controller;

import com.example.socialnetwork.domain.dto.TransactionCountsDto;
import com.example.socialnetwork.domain.dto.TransactionDemoResponseDto;
import com.example.socialnetwork.service.TransactionDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionDemoService demoService;

    @PostMapping("/without-tx")
    public TransactionDemoResponseDto withoutTransaction(@RequestParam(defaultValue = "batch") String prefix) {
        TransactionCountsDto before = demoService.getCounts();
        String errorMessage = null;
        try {
            demoService.saveRelatedEntitiesWithoutTransaction(prefix);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        TransactionCountsDto after = demoService.getCounts();
        return new TransactionDemoResponseDto("without-tx", errorMessage, before, after);
    }

    @PostMapping("/with-tx")
    public TransactionDemoResponseDto withTransaction(@RequestParam(defaultValue = "batch") String prefix) {
        TransactionCountsDto before = demoService.getCounts();
        String errorMessage = null;
        try {
            demoService.saveRelatedEntitiesWithTransaction(prefix);
        } catch (Exception exception) {
            errorMessage = exception.getMessage();
        }
        TransactionCountsDto after = demoService.getCounts();
        return new TransactionDemoResponseDto("with-tx", errorMessage, before, after);
    }
}
