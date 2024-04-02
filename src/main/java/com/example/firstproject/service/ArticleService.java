package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article delete(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if(article == null){
            return null;
        }
        articleRepository.delete(article);
        return article;
    }

    public Article update(Long id, ArticleForm dto) {
        Article entity = dto.toEntity();
        Article article = articleRepository.findById(id).orElse(null);
        if(article == null || !id.equals(article.getId())){
            return null;
        }
        article.patch(entity);
        return articleRepository.save(article);
    }

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        // id값이 존재할경우 만들지 않고 null 반환
        if(article.getId() !=null){
            return null;
        }
        return articleRepository.save(article);
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {

        List<Article> articleList = dtos.stream().map(dto->dto.toEntity()).collect(Collectors.toList());
        articleList.stream().forEach(article->articleRepository.save(article));
        articleRepository.findById(-1L).orElseThrow(()->new IllegalArgumentException("결제 실패"));
        return articleList;
    }
}
