package com.adullam.controller;

import com.adullam.domain.Member;
import com.adullam.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    MemberService memberService;

    @RequestMapping("/")
    public String home(Model model, HttpSession session){
        log.info("HOME CONTROLLER");
        return "home";
    }
}