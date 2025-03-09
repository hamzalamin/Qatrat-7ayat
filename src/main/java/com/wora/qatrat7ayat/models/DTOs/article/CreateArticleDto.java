package com.wora.qatrat7ayat.models.DTOs.article;


public record CreateArticleDto(
        String title,
        String content,
        String imageUrl,
        Long cityId
) {
}
