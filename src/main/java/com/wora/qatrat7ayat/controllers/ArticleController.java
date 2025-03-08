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


@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final IArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDto> create(@RequestBody @Valid CreateArticleDto createArticleDto){
        return new ResponseEntity<>(articleService.create(createArticleDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(articleService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ArticleDto>> findAll(@RequestParam int pageNumber, int size){
        Page<ArticleDto> articles = articleService.findAllPage(pageNumber, size);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto> update(
            @RequestBody UpdateArticleDto updateArticleDto,
            @PathVariable Long id
    ){
        return new ResponseEntity<>(articleService.update(updateArticleDto ,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id
    ){
        articleService.delete(id);
        return new ResponseEntity<>("ARTICLE with id: " + id + " deleted successfully !!", HttpStatus.OK);
    }
}
