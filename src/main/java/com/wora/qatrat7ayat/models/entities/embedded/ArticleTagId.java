package com.wora.qatrat7ayat.models.entities.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record ArticleTagId(
        @Column(name = "article_id")
        @NotNull
        Long articleId,
        @Column(name = "tag_id")
        @NotNull
        Long tagId
) {
}
