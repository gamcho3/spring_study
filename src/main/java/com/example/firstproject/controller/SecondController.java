package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/random-quote")
    public String randomQuote(Model model){
        String[] quotes = {"삶이 있는 한 희망은 있다 -키케로","산다는것 그것은 치열한 전투이다. -로망로랑","하루에 3시간을 걸으면 7년 후에 지구를 한바퀴 돌 수 있다. -사무엘존슨"};
        int random = (int)(Math.random() * quotes.length);
        model.addAttribute("randomQuote",quotes[random]);
        return "random_quote";
    }
}
