package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
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
        Article saved = articleRepository.save(article);
        System.out.println(saved);
        return "";
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        final Logger logger = LoggerFactory.getLogger(MemberController.class);
        logger.info("id= " + id);
        //TODO: 1. id를 조회해 데이터 가져오기
        // 2. 모델에 데이터 등록하기
        // 3. 뷰 페이지 반환하기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);


        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //TODO: 1. 모든 데이터 가져오기
        // 2. 모델에 데이터 등록하기
        // 3. 뷰 페이지 설정하기
        List<Article> list = articleRepository.findAll();
        model.addAttribute("articleList",list);
        return "articles/index";
    }
}
