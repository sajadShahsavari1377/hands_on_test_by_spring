package com.sajad.tddtest.tddtest.service.api;

import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MemberWalletService {

    MemberWallet save(MemberWallet memberWallet);

    List<MemberWallet> findAll();


    Optional<MemberWallet> findByMemberIdAndSymbol(long memberId, String symbol);
    Optional<MemberWallet> findById(Long walletId);

    List<MemberWallet> findByMemberId(long memberId);


    int decreaseBalance(long walletId, BigDecimal decreaseAmount);


    int increaseBalance(long walletId, BigDecimal increaseAmount);

    int thawBalance(long walletId, BigDecimal thawBalanceAmount);


    int increaseFrozenBalance(long walletId,BigDecimal increaseAmount);

    void saveAll(List<MemberWallet> memberWallets);

    //    important point : we do not have such a delete method in a real application, this is just for test
    void deleteAll();
}
