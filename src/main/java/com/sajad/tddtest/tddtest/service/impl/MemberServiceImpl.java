package com.sajad.tddtest.tddtest.service.impl;

import com.sajad.tddtest.tddtest.dao.MemberRepository;
import com.sajad.tddtest.tddtest.model.entity.Member;
import com.sajad.tddtest.tddtest.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public void saveAll(List<Member> memberList) {
        memberRepository.saveAll(memberList);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public void deleteAll() {
        memberRepository.deleteAll();
    }
}
