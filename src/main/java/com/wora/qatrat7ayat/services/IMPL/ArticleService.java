package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.exceptions.EntityNotFoundException;
import com.wora.qatrat7ayat.mappers.ArticleMapper;
import com.wora.qatrat7ayat.models.DTOs.article.ArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.CreateArticleDto;
import com.wora.qatrat7ayat.models.DTOs.article.UpdateArticleDto;
import com.wora.qatrat7ayat.models.entities.Article;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.repositories.ArticleRepository;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.services.INTER.IArticleService;
import com.wora.qatrat7ayat.services.INTER.ICityService;
import com.wora.qatrat7ayat.services.INTER.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final IProfileService profileService;
    private final ICityService cityService;

    @Override
    public ArticleDto create(CreateArticleDto createArticleDto) {
        City city = cityService.findCityEntity(createArticleDto.cityId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            throw new RuntimeException("User not authenticated");
        }
        String email = authentication.getName();
        AuthenticatedUser user = profileService.getUserByEmail(email);
        Article article = articleMapper.toEntity(createArticleDto);
        article.setUser(user);
        article.setCity(city);
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    @Override
    public ArticleDto findById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article", id));
        return articleMapper.toDto(article);
    }

    @Override
    public ArticleDto update(UpdateArticleDto updateArticleDto, Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article", id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            throw new RuntimeException("User not authenticated");
        }

        String email = authentication.getName();
        AuthenticatedUser user = profileService.getUserByEmail(email);
        Article updateArticle = article.toBuilder()
                .title(updateArticleDto.title())
                .content(updateArticleDto.content())
                .user(user)
                .build();

        return articleMapper.toDto(updateArticle);
    }

    @Override
    public List<ArticleDto> findAll(Integer pageNumber, Integer size) {
        PageRequest pageable = PageRequest.of(pageNumber, size);
        return  articleRepository.findAll(pageable).stream()
                .map(articleMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article", id));
        articleRepository.delete(article);
    }
}
