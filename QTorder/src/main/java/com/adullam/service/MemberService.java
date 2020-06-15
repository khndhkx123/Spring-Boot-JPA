package com.adullam.service;

import com.adullam.domain.Member;
import com.adullam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(Member member){
        memberRepository.save(member);
    }

    public Member signin(String mb_id, String mb_pw){
        Member member = memberRepository.findOne(mb_id);
        if(member != null && member.getMb_pw().equals(mb_pw)){
            return member;
        }
        else return null;
    }

    public void signout(HttpSession session) throws Exception{
        session.invalidate();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findOne(String mb_id){
        return memberRepository.findOne(mb_id);
    }
}
