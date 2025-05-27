package com.wora.qatrat7ayat.services.inter;

import com.wora.qatrat7ayat.models.DTOs.article.ArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.CreateArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.UpdateArticleDto;
import com.wora.qatrat7ayat.services.GenericService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IArticleService extends GenericService<CreateArticleDto, UpdateArticleDto, ArticleDto, Long> {
    Page<ArticleDto> findAllPage(Integer pageNumber, Integer size);
    void updateStatus(Long id);
    Page<ArticleDto> findArticlesByAuthenticatedUser(Integer pageNumber, Integer size);
    Page<ArticleDto> findAllApprovedArticles(Integer pageNumber, Integer size);
    List<ArticleDto> findLatestApprovedArticles(Integer limit);
}
