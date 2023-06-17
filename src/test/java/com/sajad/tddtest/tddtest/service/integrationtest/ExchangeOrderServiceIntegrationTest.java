package com.sajad.tddtest.tddtest.service.integrationtest;

import com.sajad.tddtest.tddtest.model.dto.AddOrderDto;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;
import com.sajad.tddtest.tddtest.model.dto.SelectedWalletMarketDto;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;
import com.sajad.tddtest.tddtest.model.entity.Member;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.model.exception.TestProjectBusinessException;
import com.sajad.tddtest.tddtest.service.AbstractExchangeOrderServiceTest;
import com.sajad.tddtest.tddtest.service.api.*;
import com.sajad.tddtest.tddtest.utility.ExchangeCoinConstants;
import com.sajad.tddtest.tddtest.utility.constants.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
public class ExchangeOrderServiceIntegrationTest extends AbstractExchangeOrderServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private ExchangeCoinService exchangeCoinService;
    @Autowired
    private ExchangeOrderService exchangeOrderService;



    @BeforeEach
    public void setupVariables() {
        super.memberService = this.memberService;
        super.memberWalletService = this.memberWalletService;
        super.exchangeCoinService = this.exchangeCoinService;
        super.exchangeOrderService = this.exchangeOrderService;
    }



    @BeforeEach
    public void setupMembers() {
        Member member1 = Member.builder()
                .username("user1")
                .password("pass1")
                .name("John")
                .family("Doe")
                .build();

        Member member2 = Member.builder()
                .username("user2")
                .password("pass2")
                .name("Jane")
                .family("Smith")
                .build();

        Member member3 = Member.builder()
                .username("user3")
                .password("pass3")
                .name("Michael")
                .family("Johnson")
                .build();

        Member member4 = Member.builder()
                .username("user4")
                .password("pass4")
                .name("Emily")
                .family("Davis")
                .build();

        Member member5 = Member.builder()
                .username("user5")
                .password("pass5")
                .name("David")
                .family("Wilson")
                .build();
        memberService.saveAll(List.of(member1,member2,member3,member4,member5));
    }

    @BeforeEach
    public void setupExchangeCoins() {
        ExchangeCoin exchangeCoin1 = ExchangeCoin.builder()
                .symbol("ETH/BTC")
                .coinSymbol("ETH")
                .baseSymbol("BTC")
                .fee(BigDecimal.valueOf(0.01))
                .build();

        ExchangeCoin exchangeCoin2 = ExchangeCoin.builder()
                .symbol("LTC/BTC")
                .coinSymbol("LTC")
                .baseSymbol("BTC")
                .fee(BigDecimal.valueOf(0.02))
                .build();

        ExchangeCoin exchangeCoin3 = ExchangeCoin.builder()
                .symbol("XRP/BTC")
                .coinSymbol("XRP")
                .baseSymbol("BTC")
                .fee(BigDecimal.valueOf(0.03))
                .build();

        ExchangeCoin exchangeCoin4 = ExchangeCoin.builder()
                .symbol("BNB/BTC")
                .coinSymbol("BNB")
                .baseSymbol("BTC")
                .fee(BigDecimal.valueOf(0.04))
                .build();

        ExchangeCoin exchangeCoin5 = ExchangeCoin.builder()
                .symbol("EOS/BTC")
                .coinSymbol("EOS")
                .baseSymbol("BTC")
                .fee(BigDecimal.valueOf(0.05))
                .build();
        exchangeCoinService.saveAll(List.of(exchangeCoin1,exchangeCoin2,exchangeCoin3,exchangeCoin4,exchangeCoin5));
    }

    @BeforeEach
    public void setupMemberWallets() {
        MemberWallet wallet1 = MemberWallet.builder()
                .symbol("BTC")
                .memberId(1L)
                .balance(BigDecimal.valueOf(1000))
                .frozenBalance(BigDecimal.valueOf(500))
                .build();

        MemberWallet wallet2 = MemberWallet.builder()
                .symbol("USD")
                .memberId(2L)
                .balance(BigDecimal.valueOf(2000))
                .frozenBalance(BigDecimal.valueOf(800))
                .build();

        MemberWallet wallet3 = MemberWallet.builder()
                .symbol("ETH")
                .memberId(3L)
                .balance(BigDecimal.valueOf(1500))
                .frozenBalance(BigDecimal.valueOf(600))
                .build();
        MemberWallet wallet4 = MemberWallet.builder()
                .symbol("ETH")
                .memberId(1L)
                .balance(BigDecimal.valueOf(1000))
                .frozenBalance(BigDecimal.valueOf(500))
                .build();

        MemberWallet wallet5 = MemberWallet.builder()
                .symbol("USD")
                .memberId(1L)
                .balance(BigDecimal.valueOf(2000))
                .frozenBalance(BigDecimal.valueOf(800))
                .build();
        memberWalletService.saveAll(List.of(wallet1, wallet2, wallet3));
    }

    @AfterEach
    public void removeDataAfterEachTest() {
        memberWalletService.deleteAll();
        exchangeCoinService.deleteAll();
        memberService.deleteAll();
    }

    @Test
    @Override
    public void addCorrectOrderBuyLimitOrder() {
        super.addCorrectOrderBuyLimitOrder();
    }



    @Test
    @Override
    public void addNotEnoughBalanceOrderBuyLimitOrder() {
        super.addNotEnoughBalanceOrderBuyLimitOrder();
    }

    @Test
    @Override
    public void addWalletNotFoundOrderBuyLimitOrder() {
        super.addWalletNotFoundOrderBuyLimitOrder();
    }
}
