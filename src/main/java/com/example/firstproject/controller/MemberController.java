package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
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
public class MemberController {
    @Autowired
    private MemberRepository repository;

    @GetMapping("/signup")
    public String signupPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String joinCreate(MemberForm form){
        final Logger logger = LoggerFactory.getLogger(MemberController.class);
        Member memberEntity = form.toEntity();
        Member saved = repository.save(memberEntity);
        logger.info(saved.toString());
        return "";
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        // 레포지토리에서 엔티티불러오기
        // 엔티티 뷰에 보여주기
        Member member = repository.findById(id).orElse(null);
        final Logger logger = LoggerFactory.getLogger(MemberController.class);
        logger.info(member.toString());
        //키,벨류값으로 뷰에 전달
        model.addAttribute("member",member);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        List<Member> members= (List<Member>) repository.findAll();
        model.addAttribute("members",members);
        return "members/index";
    }
}
