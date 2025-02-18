package com.wora.qatrat7ayat.repositories;

import com.wora.qatrat7ayat.models.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
