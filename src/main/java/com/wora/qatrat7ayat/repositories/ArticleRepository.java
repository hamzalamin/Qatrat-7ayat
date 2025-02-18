package com.wora.qatrat7ayat.repositories;

import com.wora.qatrat7ayat.models.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
