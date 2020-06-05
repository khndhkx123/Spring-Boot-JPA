package com.adullam.controller;

import com.adullam.domain.Member;
import com.adullam.form.MemberForm;
import com.adullam.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //회원가입 GET
    @GetMapping("/members/signup")
    public String createForm(Model model){
        log.info("CREATE FORM");
        model.addAttribute("memberForm", new MemberForm());
        return "members/signup";
    }

    //회원가입 POST
    @PostMapping("/members/signup")
    public String signup(MemberForm form){
        log.info("SIGNUP");
        Member member = new Member();
        member.setMb_id(form.mb_id);
        member.setMb_pw(form.mb_pw);
        member.setMb_name(form.mb_name);
        member.setMb_ident(2);
        memberService.signup(member);

        return "redirect:/";
    }

    //로그인 GET
    @GetMapping("/members/signin")
    public String signin_info(Model model){
        log.info("SINGING IN");
        model.addAttribute("memberForm", new MemberForm());
        return "members/signin";
    }

    //로그인 POST
    @PostMapping("/members/signin")
    public String signin(MemberForm form, Model model){
        log.info("SIGNIN");
        Member member = memberService.signin(form.getMb_id(), form.getMb_pw());

        if(member != null){
            model.addAttribute("member", member);
            return "main";
        }
        else return "redirect:/";
    }
}
