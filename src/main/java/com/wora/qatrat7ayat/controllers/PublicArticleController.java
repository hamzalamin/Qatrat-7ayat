package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.article.ArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.CreateArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.UpdateArticleDto;
import com.wora.qatrat7ayat.services.INTER.IArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/public/articles")
@RequiredArgsConstructor
public class PublicArticleController {
    private final IArticleService articleService;

    @GetMapping()
    public ResponseEntity<Page<ArticleDto>> getApprovedArticles(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<ArticleDto> articles = articleService.findAllApprovedArticles(pageNumber, size);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ArticleDto>> getLatestApprovedArticles(
            @RequestParam(defaultValue = "3") Integer limit) {
        List<ArticleDto> articles = articleService.findLatestApprovedArticles(limit);
        return ResponseEntity.ok(articles);
    }
}
