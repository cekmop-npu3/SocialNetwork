package com.example.socialnetwork.controller;

import com.example.socialnetwork.domain.dto.PostDto;
import com.example.socialnetwork.service.TransactionDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/demo/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionDemoService demoService;

    @PostMapping("/without-tx")
    public String withoutTransaction(@RequestBody PostDto postDto, @RequestParam String comment) {
        try {
            demoService.createPostAndCommentWithoutTransaction(postDto, comment);
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage() + " (пост мог сохраниться)";
        }
        return "Успех (не должен был выполниться)";
    }

    @PostMapping("/with-tx")
    public String withTransaction(@RequestBody PostDto postDto, @RequestParam String comment) {
        try {
            demoService.createPostAndCommentWithTransaction(postDto, comment);
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage() + " (всё откачено)";
        }
        return "Успех (не должен был выполниться)";
    }
}