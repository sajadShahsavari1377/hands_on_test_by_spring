package com.sajad.tddtest.tddtest.utility.mockers.service;

import com.sajad.tddtest.tddtest.dao.MemberWalletRepository;
import com.sajad.tddtest.tddtest.service.api.MemberWalletService;
import com.sajad.tddtest.tddtest.utility.mockers.Mocker;
import com.sajad.tddtest.tddtest.utility.mockers.dao.MemberWalletRepositoryMocker;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class MemberWalletServiceMocker implements Mocker<MemberWalletService> {
    @Override
    public MemberWalletService getMock() {
        MemberWalletService memberWalletService = Mockito.mock(MemberWalletService.class);
        MemberWalletRepository memberWalletRepository = new MemberWalletRepositoryMocker().getMock();
        when(memberWalletService.findByMemberId(anyLong())).thenAnswer(invocationOnMock ->
             memberWalletRepository.findByMemberId(invocationOnMock.getArgument(0, Long.class))
        );

        when(memberWalletService.findByMemberIdAndSymbol(anyLong(),anyString())).thenAnswer(invocationOnMock ->
                memberWalletRepository.findByMemberIdAndSymbol(invocationOnMock.getArgument(0, Long.class),invocationOnMock.getArgument(1, String.class))
        );

        when(memberWalletService.findById(anyLong())).thenAnswer(invocationOnMock ->
                memberWalletRepository.findById(invocationOnMock.getArgument(0, Long.class))
        );

        when(memberWalletService.increaseBalance(anyLong(),any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            return memberWalletRepository.increaseBalance(invocationOnMock.getArgument(0, Long.class), invocationOnMock.getArgument(1, BigDecimal.class));
        });

        when(memberWalletService.decreaseBalance(anyLong(), any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            return memberWalletRepository.decreaseBalance(invocationOnMock.getArgument(0, Long.class), invocationOnMock.getArgument(1, BigDecimal.class));
        });

        when(memberWalletService.increaseFrozenBalance(anyLong(),any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            return memberWalletRepository.increaseFrozenBalance(invocationOnMock.getArgument(0, Long.class), invocationOnMock.getArgument(1, BigDecimal.class));
        });

        when(memberWalletService.thawBalance(anyLong(),any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            return memberWalletRepository.thawBalance(invocationOnMock.getArgument(0, Long.class), invocationOnMock.getArgument(1, BigDecimal.class));
        });
        when(memberWalletService.findAll()).thenAnswer(invocationOnMock -> memberWalletRepository.findAll());
        return memberWalletService;
    }
}
