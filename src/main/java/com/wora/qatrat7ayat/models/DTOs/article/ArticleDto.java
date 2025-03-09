package com.wora.qatrat7ayat.models.DTOs.article;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.DTOs.user.EmbeddedProfileDto;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        String title,
        String content,
        String imageUrl,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt,
        EmbeddedProfileDto user,
        EmbeddedCityDto city
) {
}
