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
        System.out.println(member.getMb_id() + " " + member.getMb_name() + " " + member.getMb_pw() + " " + member.getMb_ident());
        memberRepository.save(member);
    }
}
