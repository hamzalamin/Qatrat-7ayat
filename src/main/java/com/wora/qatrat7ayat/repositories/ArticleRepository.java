package com.wora.qatrat7ayat.repositories;

import com.wora.qatrat7ayat.models.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByUserId(Long userId, Pageable pageable);
    @Query("SELECT a FROM Article a WHERE CAST(a.status AS string) = :status")
    Page<Article> findByStatus(@Param("status") String status, Pageable pageable);
    @Query("SELECT a FROM Article a WHERE CAST(a.status AS string) = :status ORDER BY a.publishedAt DESC")
    List<Article> findByStatusOrderByPublishedAtDesc(@Param("status") String status, Pageable pageable);
}
