package com.sajad.tddtest.tddtest.utility.mockers.dao;

import com.sajad.tddtest.tddtest.dao.MemberRepository;
import com.sajad.tddtest.tddtest.model.entity.Member;
import com.sajad.tddtest.tddtest.utility.mockers.Mocker;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static com.sajad.tddtest.tddtest.utility.MemberConstants.*;

public class MemberRepositoryMocker implements Mocker<MemberRepository> {

    private List<Member> members;
    private Member ali;
    private Member jack;
    private Member james;
    private Member mehdi;
    private Member sajad;

    public MemberRepositoryMocker() {
        ali = Member.builder().id(1l).name(NAMES.ALI).family(FAMILY.ALI)
                .username(USERNAME.ALI).password(PASSWORD.ALI)
                .build();
        jack = Member.builder().id(1l).name(NAMES.JACK).family(FAMILY.JACK)
                .username(USERNAME.JACK).password(PASSWORD.JACK)
                .build();
        james = Member.builder().id(1l).name(NAMES.JAMES).family(FAMILY.JAMES)
                .username(USERNAME.JAMES).password(PASSWORD.JAMES)
                .build();
        mehdi = Member.builder().id(1l).name(NAMES.MEHDI).family(FAMILY.MEHDI)
                .username(USERNAME.MEHDI).password(PASSWORD.MEHDI)
                .build();
        sajad = Member.builder().id(1l).name(NAMES.SAJAD).family(FAMILY.SAJAD)
                .username(USERNAME.SAJAD).password(PASSWORD.SAJAD)
                .build();
        members = List.of(ali,jack,james,mehdi,sajad);
    }



    @Override
    public MemberRepository getMock() {
        MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
        when(memberRepository.findById(anyLong())).thenAnswer(invocationOnMock -> {
            Long memberId = invocationOnMock.getArgument(0, Long.class);
            return members.stream().filter(member -> member.getId().equals(memberId)).findAny();
        });
        when(memberRepository.findByUsername(anyString())).thenAnswer(invocationOnMock -> {
            String username = invocationOnMock.getArgument(0, String.class);
            return members.stream().filter(member -> member.getUsername().equals(username)).findAny();
        });
        return memberRepository;
    }
}
