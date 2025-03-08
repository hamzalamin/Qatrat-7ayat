package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.article.ArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.CreateArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.UpdateArticleDto;
import com.wora.qatrat7ayat.models.entities.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends GenericMapper<Article, ArticleDto> {
    Article toEntity(ArticleDto dto);
    Article toEntity(CreateArticleDto dto);
    Article toEntity(UpdateArticleDto dto);
    @Mapping(target = "city", source = "city")
    ArticleDto toDto(Article article);
}
