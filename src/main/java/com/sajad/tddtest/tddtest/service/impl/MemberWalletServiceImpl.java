package com.sajad.tddtest.tddtest.service.impl;

import com.sajad.tddtest.tddtest.dao.MemberWalletRepository;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.service.api.MemberWalletService;
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

    // TODO: 6/17/2023 this method is just for unit test
    @Override
    public List<MemberWallet> findAll() {
        return memberWalletRepository.findAll();
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


    @Override
    //    important point : we do not have such a delete method in a real application, this is just for test
    public void deleteAll() {

        memberWalletRepository.deleteAll();
    }
}
