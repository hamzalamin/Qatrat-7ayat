package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.mappers.ArticleMapper;
import com.wora.qatrat7ayat.models.DTOs.article.ArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.CreateArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.UpdateArticleDto;
import com.wora.qatrat7ayat.repositories.ArticleRepository;
import com.wora.qatrat7ayat.services.INTER.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDto create(CreateArticleDto createArticleDto) {
        return null;
    }

    @Override
    public ArticleDto findById(Long aLong) {
        return null;
    }

    @Override
    public ArticleDto update(UpdateArticleDto updateArticleDto, Long aLong) {
        return null;
    }

    @Override
    public List<ArticleDto> findAll(Integer pageNumber, Integer size) {
        return List.of();
    }

    @Override
    public void delete(Long aLong) {

    }
}
