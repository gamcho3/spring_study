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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        // 리다이렉트 위치 작성
        return "redirect:/articles/" + saved.getId();
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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        System.out.println(model);
        model.addAttribute("article",article);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        System.out.println(form);
        // 1. DTO를 엔티티에 변환
        // 2. 엔티티를 db에 저장
        // 3. 수정결과 리다이렉트

        Article article = form.toEntity();
        Article target = articleRepository.findById(article.getId()).orElse(null);
        if(target != null){
            articleRepository.save(target);
        }
        System.out.println("result :" + target.toString());
        return "redirect:/articles/" + target.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청");
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null){
            articleRepository.delete(target);
            // 한번 사용돼고 바로 날라가는 데이터
            rttr.addFlashAttribute("msg","삭제됐습니다.");
        }
        log.info(target.toString());

        return "redirect:/articles";
    }
}
