package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.article.ArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.CreateArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.UpdateArticleDto;
import com.wora.qatrat7ayat.services.GenericService;
import org.springframework.data.domain.Page;

public interface IArticleService extends GenericService<CreateArticleDto, UpdateArticleDto, ArticleDto, Long> {
    Page<ArticleDto> findAllPage(Integer pageNumber, Integer size);
}
