package com.wora.qatrat7ayat.models.DTOs.article;

import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.DTOs.user.EmbeddedProfileDto;

public record EmbeddedArticleDto(
        String title,
        String content,
        String imageUrl,
        EmbeddedProfileDto user,
        EmbeddedCityDto city
) {
}
