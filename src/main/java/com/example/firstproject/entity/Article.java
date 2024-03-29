package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.catalina.users.GenericRole;


@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

    public void patch(Article article){
        if(article.title !=null){
            this.title = article.title;
        }

        if(article.content !=null){
            this.content = article.content;
        }
    }

}
