package com.sajad.tddtest.tddtest.service.integrationtest;

import com.sajad.tddtest.tddtest.dao.ExchangeOrderRepository;
import com.sajad.tddtest.tddtest.dao.MemberRepository;
import com.sajad.tddtest.tddtest.model.dto.AddOrderDto;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;
import com.sajad.tddtest.tddtest.model.entity.Member;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.model.exception.TestProjectBusinessException;
import com.sajad.tddtest.tddtest.service.api.ExchangeCoinService;
import com.sajad.tddtest.tddtest.service.api.ExchangeOrderService;
import com.sajad.tddtest.tddtest.service.api.MemberWalletService;
import com.sajad.tddtest.tddtest.service.impl.ExchangeOrderServiceImpl;
import com.sajad.tddtest.tddtest.utility.ExchangeCoinConstants;
import com.sajad.tddtest.tddtest.utility.MemberConstants;
import com.sajad.tddtest.tddtest.utility.constants.Constants;
import com.sajad.tddtest.tddtest.utility.mockers.dao.ExchangeOrderRepositoryMocker;
import com.sajad.tddtest.tddtest.utility.mockers.service.ExchangeCoinServiceMocker;
import com.sajad.tddtest.tddtest.utility.mockers.service.MemberWalletServiceMocker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class ExchangeOrderServiceUnitTest {

    @Autowired
    private ExchangeCoinService exchangeCoinService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private ExchangeOrderService exchangeOrderService;
    List<Member> members;
    List<ExchangeCoin> exchangeCoins;
    List<MemberWallet> memberWallets;



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
        members=List.of(member1,member2,member3,member4,member5);
        memberRepository.saveAll(members);
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
        exchangeCoins=List.of(exchangeCoin1,exchangeCoin2,exchangeCoin3,exchangeCoin4,exchangeCoin5);
        exchangeCoinService.saveAll(exchangeCoins);
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
        memberWallets=List.of(wallet1, wallet2, wallet3);
        memberWalletService.saveAll(memberWallets);
    }


    @Test
    public void addCorrectOrderBuyLimitOrder() {
        MemberWallet wallet1 = memberWalletService.findById(1l).get();
        BigDecimal beforeOperationBalance = wallet1.getBalance();
        BigDecimal beforeOperationFozenBalance = wallet1.getFrozenBalance();
        BigDecimal price = BigDecimal.valueOf(2l);
        BigDecimal amount = BigDecimal.valueOf(3l);
        short direction = Constants.EXCHANGE_ORDER.DIRECTION.BUY;
        AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(wallet1.getMemberId()).type((short) 1).direction(direction)
                .symbol(ExchangeCoinConstants.SYMBOLS.ETH_BTC)
                .build();
        GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        Assertions.assertNotNull(generalOrderInfo);
        Assertions.assertNotNull(generalOrderInfo.getId());
        wallet1 = memberWalletService.findById(1l).get();
        Assertions.assertEquals(wallet1.getBalance(),beforeOperationBalance.subtract(price.multiply(amount)));
        Assertions.assertEquals(wallet1.getFrozenBalance(),beforeOperationFozenBalance.add(price.multiply(amount)));
    }

    @Test
    public void addNotEnoughBalanceOrderBuyLimitOrder() {
        MemberWallet wallet1 = memberWalletService.findById(1l).get();
        BigDecimal beforeOperationBalance = wallet1.getBalance();
        BigDecimal beforeOperationFozenBalance = wallet1.getFrozenBalance();
        TestProjectBusinessException testProjectBusinessException = Assertions.assertThrows(TestProjectBusinessException.class, () -> {
            BigDecimal price = BigDecimal.valueOf(1000);
            BigDecimal amount = BigDecimal.TEN;
            AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(wallet1.getMemberId()).type((short) 1).direction((short) 1)
                    .symbol(ExchangeCoinConstants.SYMBOLS.ETH_BTC)
                    .build();
            GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        });
        Assertions.assertEquals(testProjectBusinessException.getMessage(), Constants.EXCEPTION_KEYS.NOT_ENOUGH_BALANCE);
        Assertions.assertEquals(beforeOperationBalance,wallet1.getBalance());
        Assertions.assertEquals(beforeOperationFozenBalance,wallet1.getFrozenBalance());
    }

    @Test
    public void addWalletNotFoundOrderBuyLimitOrder() {
        TestProjectBusinessException testProjectBusinessException = Assertions.assertThrows(TestProjectBusinessException.class, () -> {
            BigDecimal price = BigDecimal.valueOf(1000);
            BigDecimal amount = BigDecimal.TEN;
            short direction = Constants.EXCHANGE_ORDER.DIRECTION.BUY;
            AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(2l).type((short) 1).direction(direction)
                    .symbol("EOS/BTC")
                    .build();
            GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        });
        Assertions.assertEquals(testProjectBusinessException.getMessage(), Constants.EXCEPTION_KEYS.WALLET_NOT_FOUND);
    }
}
