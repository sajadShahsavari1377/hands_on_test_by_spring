package com.sajad.tddtest.tddtest.service.api;

import com.sajad.tddtest.tddtest.dao.MemberWalletRepository;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberWalletServiceImpl implements MemberWalletService {
    private final MemberWalletRepository memberWalletRepository;

    @Override
    public MemberWallet save(MemberWallet memberWallet) {
        return memberWalletRepository.save(memberWallet);
    }

    @Override
    public Optional<MemberWallet> findByMemberIdAndSymbol(long memberId, String symbol) {
        return memberWalletRepository.findByMemberIdAndSymbol(memberId, symbol);
    }

    @Override
    public Optional<MemberWallet> findById(Long walletId) {
        return memberWalletRepository.findById(walletId);
    }

    @Override
    public List<MemberWallet> findByMemberId(long memberId) {
        return memberWalletRepository.findByMemberId(memberId);
    }

    @Override
    public int decreaseBalance(long walletId, BigDecimal decreaseAmount) {
        return memberWalletRepository.decreaseBalance(walletId, decreaseAmount);
    }

    @Override
    public int increaseBalance(long walletId, BigDecimal increaseAmount) {
        return memberWalletRepository.increaseBalance(walletId, increaseAmount);
    }

    @Override
    public int thawBalance(long walletId, BigDecimal thawBalanceAmount) {
        return memberWalletRepository.thawBalance(walletId, thawBalanceAmount);

    }

    @Override
    public int increaseFrozenBalance(long walletId, BigDecimal increaseAmount) {
        return memberWalletRepository.increaseFrozenBalance(walletId, increaseAmount);
    }

    @Override
    public void saveAll(List<MemberWallet> memberWallets) {
        memberWalletRepository.saveAll(memberWallets);
    }
}
