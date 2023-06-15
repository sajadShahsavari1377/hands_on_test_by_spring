package com.sajad.tddtest.tddtest.dao;

import com.sajad.tddtest.tddtest.base.BaseRepository;
import com.sajad.tddtest.tddtest.model.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends BaseRepository<Member> {
    Optional<Member> findByUsername(String username);
}
