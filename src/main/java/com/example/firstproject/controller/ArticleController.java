package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    //의존성 주입
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //TODO : DTO를 엔티티로 변환
        //TODO : 리포지토리로 엔티티를 db에 저장
        Article article = form.toEntity();
        System.out.println(article.toString());
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        System.out.println(form.toString());
        return "";
    }
}
