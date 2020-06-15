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
        log.info("SIGNUP : POST");
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
        log.info("SINGIN : GET");
        model.addAttribute("memberForm", new MemberForm());
        return "members/signin";
    }

    //로그인 POST
    @PostMapping("/members/signin")
    public String signin(MemberForm form, Model model, HttpServletRequest request){
        log.info("SIGNIN : POST");
        Member member = memberService.signin(form.getMb_id(), form.getMb_pw());
        HttpSession session = request.getSession();

        if(member != null){
            model.addAttribute("member", member);
            session.setAttribute("mb_id",member.getMb_id());
            session.setAttribute("mb_pw",member.getMb_pw());
            return "main";
        }
        else return "redirect:/";
    }

    @GetMapping("/members/signout")
    public String signout(HttpSession session) throws Exception{
        log.info("LOGOUT : GET");

        memberService.signout(session);

        return "redirect:/";
    }
}
