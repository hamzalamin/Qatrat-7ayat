package com.wora.qatrat7ayat.models.DTOs.article;


public record UpdateArticleDto(
        String title,
        String content,
        Long cityId
) {
}
