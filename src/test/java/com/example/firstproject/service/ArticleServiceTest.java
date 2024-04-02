package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.TestClass;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 예상 데이터
        List<Article> articles = articleService.index();
        Article a = new Article(1L,"QWERTY","1111");
        Article b = new Article(2L,"QWERTY2","@222");
        Article c = new Article(3L,"QWERTY3","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 실제 데이터
        // 비교 및 검증
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공() {
        Long id = 1L;
        // 예상데이터
        Article expected = new Article(id,"QWERTY","1111");

        // 실제 데이터
        Article article = articleService.show(id);
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_실패_존재하지않는_id_입력(){
        Long id = -1L;
        Article expected = null;
        Article article = articleService.show(id);
        assertEquals(expected,article);
    }

    @Transactional
    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상데이터
        String title = "ddd";
        String content = "444";
        ArticleForm form = new ArticleForm(title,content,null);
        Article expected = new Article(4L,title,content);
        // 2. 실제 데이터
        Article article = articleService.create(form);
        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    // 변경된 데이터 처음으로 되돌리기
    @Transactional

    @Test
    void create_실패_id가_포함된_dto_입력() {
    // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라";
        String content = "444";
        ArticleForm dto = new ArticleForm(title,content,id);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력(){
        // 1. 예상 데이터
        Long id = 1L;
        String title = "수정";
        String content = "수정";
        ArticleForm dto = new ArticleForm(title,content,id);
        Article expected = new Article(id,title,content);
        // 2. 실제 데이터
        Article article = articleService.update(id,dto);
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_만있는_dto_입력(){
        // 1. 예상 데이터
        Long id = 1L;
        String title = "수정";

        ArticleForm dto = new ArticleForm(title,null,id);
        Article expected = new Article(id,title,"1111");
        // 2. 실제 데이터
        Article article = articleService.update(id,dto);
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지않는_id_의_dto_입력(){
        // 1. 예상 데이터
        Long id = -1L;
        String title = "수정";
        String content = "수정";
        ArticleForm dto = new ArticleForm(title,content,id);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.update(id,dto);
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id입력(){
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id,"QWERTY","1111");
        // 2. 실제 데이터
        Article article = articleService.delete(id);
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void delete_성공_존재하지않는_id입력(){
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.delete(id);
        assertEquals(expected,article);
    }


}