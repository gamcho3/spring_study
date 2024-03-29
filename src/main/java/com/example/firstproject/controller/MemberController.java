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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/members/" + saved.getId();
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

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        System.out.println(id);
        System.out.println(model);
        //1. id를 이용하여 엔티티 검색
        //2. 엔티티를 뷰에 전송

        Member memberEntity = repository.findById(id).orElse(null);
        model.addAttribute("member",memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        // 1. DTO를 엔티티로 변환
        // 2. 레포지토리에서 DTO 검색
        // 3. null 체크후 저장
        // 4. redirect로 페이지 전환
        Member memberEntity = form.toEntity();
        repository.findById(memberEntity.getId()).ifPresent(target -> repository.save(memberEntity));

        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Member target = repository.findById(id).orElse(null);
        if(target !=null){
            repository.delete(target);
        }
        rttr.addFlashAttribute("msg","삭제 됐습니다.!");
        return "redirect:/members";
    }
}
