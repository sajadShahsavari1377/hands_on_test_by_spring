package com.sajad.tddtest.tddtest.utility.mockers.dao;

import com.sajad.tddtest.tddtest.dao.MemberWalletRepository;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.model.exception.TestProjectBusinessException;
import com.sajad.tddtest.tddtest.utility.constants.Constants;
import com.sajad.tddtest.tddtest.utility.mockers.Mocker;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sajad.tddtest.tddtest.utility.MemberConstants.*;
import static com.sajad.tddtest.tddtest.utility.CoinConstants.*;
import static com.sajad.tddtest.tddtest.utility.constants.Constants.EXCEPTION_KEYS.WALLET_NOT_FOUND;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class MemberWalletRepositoryMocker implements Mocker<MemberWalletRepository> {

    final List<MemberWallet> memberWallets;
    final MemberWallet aliBtc = MemberWallet.builder().id(1l).memberId(IDs.ALI).symbol(UNITS.BTC).balance(BigDecimal.valueOf(10))
            .frozenBalance(BigDecimal.ZERO).build();
    final MemberWallet aliEth = MemberWallet.builder().id(2l).memberId(IDs.ALI).symbol(UNITS.ETH).balance(BigDecimal.valueOf(15))
            .frozenBalance(BigDecimal.valueOf(2)).build();
    final MemberWallet aliShib = MemberWallet.builder().id(3l).memberId(IDs.ALI).symbol(UNITS.SHIB).balance(BigDecimal.ZERO)
            .frozenBalance(BigDecimal.TEN).build();
    final MemberWallet aliBtcLike = MemberWallet.builder().id(4l).memberId(IDs.ALI).symbol(UNITS.BTC_LIKE).balance(BigDecimal.ZERO)
            .frozenBalance(BigDecimal.ZERO).build();

    final MemberWallet mehdiEth = MemberWallet.builder().id(5l).memberId(IDs.MEHDI).symbol(UNITS.ETH).balance(BigDecimal.valueOf(100))
            .frozenBalance(BigDecimal.ZERO).build();
    final MemberWallet mehdiShib = MemberWallet.builder().id(6l).memberId(IDs.MEHDI).symbol(UNITS.SHIB).balance(BigDecimal.ZERO)
            .frozenBalance(BigDecimal.ZERO).build();
    final MemberWallet sajadBtc = MemberWallet.builder().id(7l).memberId(IDs.SAJAD).symbol(UNITS.BTC).balance(BigDecimal.valueOf(1000))
            .frozenBalance(BigDecimal.ZERO).build();

    public MemberWalletRepositoryMocker() {
        memberWallets = List.of(aliBtc,aliEth,aliShib,aliBtcLike,mehdiEth,mehdiShib,sajadBtc);
    }


    @Override
    public MemberWalletRepository getMock() {
        MemberWalletRepository memberWalletRepository = Mockito.mock(MemberWalletRepository.class);
        when(memberWalletRepository.findByMemberId(anyLong())).thenAnswer(invocationOnMock -> {
            Long memberId = invocationOnMock.getArgument(0, Long.class);
            return memberWallets.stream().filter(memberWallet -> memberWallet.getMemberId().equals(memberId)).collect(Collectors.toList());
        });
        when(memberWalletRepository.findByMemberIdAndSymbol(anyLong(),anyString())).thenAnswer(invocationOnMock -> {
            Long memberId = invocationOnMock.getArgument(0, Long.class);
            String symbol = invocationOnMock.getArgument(1, String.class);
            return memberWallets.stream().filter(memberWallet -> memberWallet.getMemberId().equals(memberId) && memberWallet.getSymbol().equals(symbol))
                    .findAny();
        });

        when(memberWalletRepository.findById(anyLong())).thenAnswer(invocationOnMock -> {
            Long walletId = invocationOnMock.getArgument(0, Long.class);
            return findByWalletId(walletId);
        });


        when(memberWalletRepository.increaseBalance(anyLong(), ArgumentMatchers.any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            Long walletId = invocationOnMock.getArgument(0, Long.class);
            BigDecimal increaseAmount = invocationOnMock.getArgument(1, BigDecimal.class);
            MemberWallet foundMemberWallet=findByWalletId(walletId).orElseThrow(()-> new TestProjectBusinessException(WALLET_NOT_FOUND));
            synchronized (foundMemberWallet) {
                foundMemberWallet.setBalance(foundMemberWallet.getBalance().add(increaseAmount.abs()));
            }
            return 1;
        });


        when(memberWalletRepository.increaseFrozenBalance(anyLong(), ArgumentMatchers.any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            Long walletId = invocationOnMock.getArgument(0, Long.class);
            BigDecimal increaseAmount = invocationOnMock.getArgument(1, BigDecimal.class);
            MemberWallet foundMemberWallet=findByWalletId(walletId).orElseThrow(()-> new TestProjectBusinessException(WALLET_NOT_FOUND));
            synchronized (foundMemberWallet) {
                foundMemberWallet.setFrozenBalance(foundMemberWallet.getFrozenBalance().add(increaseAmount.abs()));
            }
            return 1;
        });

        when(memberWalletRepository.decreaseBalance(anyLong(), ArgumentMatchers.any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            {
                Long walletId = invocationOnMock.getArgument(0, Long.class);
                BigDecimal decreaseAmount = invocationOnMock.getArgument(1, BigDecimal.class);
                Optional<MemberWallet> foundMemberWalletMono=findByWalletId(walletId);
                MemberWallet memberWallet = foundMemberWalletMono.orElseThrow(() -> new TestProjectBusinessException(WALLET_NOT_FOUND));
                synchronized (memberWallet) {
                    if (memberWallet.getBalance().compareTo(decreaseAmount.abs()) < 0) {
                        throw new TestProjectBusinessException(Constants.EXCEPTION_KEYS.NOT_ENOUGH_BALANCE);
                    }
                    memberWallet.setBalance(memberWallet.getBalance().subtract(decreaseAmount.abs()));
                }
                return 1;
            }
        });

        when(memberWalletRepository.thawBalance(anyLong(), ArgumentMatchers.any(BigDecimal.class))).thenAnswer(invocationOnMock -> {
            {
                Long walletId = invocationOnMock.getArgument(0, Long.class);
                BigDecimal thawBalanceAmount = invocationOnMock.getArgument(1, BigDecimal.class);
                MemberWallet foundMemberWallet=findByWalletId(walletId).orElseThrow(()-> new TestProjectBusinessException(WALLET_NOT_FOUND));
                synchronized (foundMemberWallet) {
                    if (foundMemberWallet.getFrozenBalance().compareTo(thawBalanceAmount.abs()) < 0) {
                        throw new TestProjectBusinessException(Constants.EXCEPTION_KEYS.NOT_ENOUGH_FROZEN_BALANCE);
                    }
                    foundMemberWallet.setFrozenBalance(foundMemberWallet.getFrozenBalance().subtract(thawBalanceAmount.abs()));
                    foundMemberWallet.setBalance(foundMemberWallet.getBalance().add(thawBalanceAmount.abs()));
                }
                return 1;
            }
        });


        return memberWalletRepository;
    }

    private Optional<MemberWallet> findByWalletId(Long walletId) {
        return memberWallets.stream().filter(memberWallet -> memberWallet.getId().equals(walletId)).findAny();
    }


}
