package com.sajad.tddtest.tddtest.service.api;


import com.sajad.tddtest.tddtest.model.entity.Member;

import java.util.List;

public interface MemberService {

    void saveAll(List<Member> memberList);

    Member save(Member member);

    List<Member> findAll();

    void deleteAll();
}
