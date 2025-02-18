package com.wora.qatrat7ayat.models.DTOs.article;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.DTOs.user.EmbeddedProfileDto;

public record UpdateArticleDto(
        String title,
        String content,
        String publishedAt,
        EmbeddedProfileDto user,
        EmbeddedCityDto city

) {
}
