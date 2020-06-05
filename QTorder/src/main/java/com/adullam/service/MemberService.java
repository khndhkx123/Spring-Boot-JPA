package com.adullam.service;

import com.adullam.domain.Member;
import com.adullam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
