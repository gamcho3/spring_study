package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository repository;

    @PostMapping("/join")
    public String joinCreate(MemberForm form){
        Member memberEntity = form.toEntity();
        Member saved = repository.save(memberEntity);
        System.out.println(saved.toString());
        return "";
    }
}
