package com.sajad.tddtest.tddtest.dao;

import com.sajad.tddtest.tddtest.base.BaseRepository;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberWalletRepository extends BaseRepository<MemberWallet> {
    Optional<MemberWallet> findByMemberIdAndSymbol(long memberId, String symbol);

    List<MemberWallet> findByMemberId(long memberId);
    @Modifying
    @Query("update MemberWallet mw set mw.balance=mw.balance-:decreaseAmount where mw.id=:walletId and mw.balance-:decreaseAmount>0 ")
    int decreaseBalance(long walletId, BigDecimal decreaseAmount);
    @Modifying
    @Query("update MemberWallet mw set mw.balance=mw.balance+:increaseAmount where mw.id=:walletId")
    int increaseBalance(long walletId, BigDecimal increaseAmount);
    @Modifying
    @Query("update MemberWallet mw set mw.frozenBalance=mw.frozenBalance-:thawBalanceAmount , mw.balance=mw.balance+:thawBalanceAmount where mw.id=:walletId and mw.frozenBalance-:thawBalanceAmount > 0 ")
    int thawBalance(long walletId, BigDecimal thawBalanceAmount);
    @Modifying
    @Query("update MemberWallet mw set mw.frozenBalance=mw.frozenBalance+:increaseAmount where mw.id=:walletId")
    int increaseFrozenBalance(long walletId,BigDecimal increaseAmount);
}
