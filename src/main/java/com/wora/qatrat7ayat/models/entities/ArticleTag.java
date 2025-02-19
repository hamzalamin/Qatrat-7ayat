package com.wora.qatrat7ayat.models.entities;

import com.wora.qatrat7ayat.models.entities.embedded.ArticleTagId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "article_tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTag {
    @EmbeddedId
    private ArticleTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private Article article;
}
