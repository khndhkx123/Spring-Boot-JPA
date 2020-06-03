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

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/signup")
    public String createForm(Model model){
        log.info("CREATE FORM");
        model.addAttribute("memberForm", new MemberForm());
        return "members/signup";
    }

    @PostMapping("/members/signup")
    public String signup(MemberForm form){
        log.info("SIGNIN");
        Member member = new Member();
        member.setMb_id(form.mb_id);
        member.setMb_pw(form.mb_pw);
        member.setMb_name(form.mb_name);
        member.setMb_ident(2);
        memberService.signup(member);

        return "redirect:/";
    }
}
