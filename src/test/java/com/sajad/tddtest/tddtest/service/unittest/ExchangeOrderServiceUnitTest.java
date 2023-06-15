package com.sajad.tddtest.tddtest.service.unittest;

import com.sajad.tddtest.tddtest.dao.ExchangeOrderRepository;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.model.exception.TestProjectBusinessException;
import com.sajad.tddtest.tddtest.service.api.ExchangeCoinService;
import com.sajad.tddtest.tddtest.service.api.ExchangeOrderService;
import com.sajad.tddtest.tddtest.service.api.MemberWalletService;
import com.sajad.tddtest.tddtest.service.impl.ExchangeOrderServiceImpl;
import com.sajad.tddtest.tddtest.utility.ExchangeCoinConstants;
import com.sajad.tddtest.tddtest.model.dto.AddOrderDto;
import com.sajad.tddtest.tddtest.utility.MemberConstants;
import com.sajad.tddtest.tddtest.utility.constants.Constants;
import com.sajad.tddtest.tddtest.utility.mockers.dao.ExchangeOrderRepositoryMocker;
import com.sajad.tddtest.tddtest.utility.mockers.service.ExchangeCoinServiceMocker;
import com.sajad.tddtest.tddtest.utility.mockers.service.MemberWalletServiceMocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
public class ExchangeOrderServiceUnitTest {

    @Mock
    private ExchangeCoinService exchangeCoinService;
    @Mock
    private MemberWalletService memberWalletService;
    private ExchangeOrderService exchangeOrderService;


    public ExchangeOrderServiceUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setup() {
        exchangeCoinService = new ExchangeCoinServiceMocker().getMock();
        memberWalletService = new MemberWalletServiceMocker().getMock();
        ExchangeOrderRepository exchangeOrderRepository = new ExchangeOrderRepositoryMocker().getMock();
        /* here I am coupling the exchangeOrderService to ExchangeOrderServiceImpl which is not good , that's because I want to mock underlying services , if I was doing an integration test
        it would not be need to (new) it explicitly */
        exchangeOrderService = new ExchangeOrderServiceImpl(memberWalletService,exchangeCoinService,exchangeOrderRepository);
    }

    @Test
    public void addCorrectOrderBuyLimitOrder() {
        MemberWallet aliBtcWallet = memberWalletService.findById(1l).get();
        BigDecimal aliBtcBeforeOperationBalance = aliBtcWallet.getBalance();
        BigDecimal aliBtcBeforeOperationFozenBalance = aliBtcWallet.getFrozenBalance();
        BigDecimal price = BigDecimal.valueOf(2l);
        BigDecimal amount = BigDecimal.valueOf(3l);
        short direction = Constants.EXCHANGE_ORDER.DIRECTION.BUY;
        AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(MemberConstants.IDs.ALI).type((short) 1).direction(direction)
                .symbol(ExchangeCoinConstants.SYMBOLS.ETH_BTC)
                .build();
        GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        Assertions.assertNotNull(generalOrderInfo);
        Assertions.assertNotNull(generalOrderInfo.getId());
        Assertions.assertEquals(aliBtcWallet.getBalance(),aliBtcBeforeOperationBalance.subtract(price.multiply(amount)));
        Assertions.assertEquals(aliBtcWallet.getFrozenBalance(),aliBtcBeforeOperationFozenBalance.add(price.multiply(amount)));
    }

    @Test
    public void addNotEnoughBalanceOrderBuyLimitOrder() {
        MemberWallet aliBtcWallet = memberWalletService.findById(1l).get();
        BigDecimal aliBtcBeforeOperationBalance = aliBtcWallet.getBalance();
        BigDecimal aliBtcBeforeOperationFozenBalance = aliBtcWallet.getFrozenBalance();
        TestProjectBusinessException testProjectBusinessException = Assertions.assertThrows(TestProjectBusinessException.class, () -> {
            BigDecimal price = BigDecimal.valueOf(1000);
            BigDecimal amount = BigDecimal.TEN;
            AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(MemberConstants.IDs.ALI).type((short) 1).direction((short) 1)
                    .symbol(ExchangeCoinConstants.SYMBOLS.ETH_BTC)
                    .build();
            GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        });
        Assertions.assertEquals(testProjectBusinessException.getMessage(), Constants.EXCEPTION_KEYS.NOT_ENOUGH_BALANCE);
        Assertions.assertEquals(aliBtcBeforeOperationBalance,aliBtcWallet.getBalance());
        Assertions.assertEquals(aliBtcBeforeOperationFozenBalance,aliBtcWallet.getFrozenBalance());
    }

    @Test
    public void addWalletNotFoundOrderBuyLimitOrder() {
        TestProjectBusinessException testProjectBusinessException = Assertions.assertThrows(TestProjectBusinessException.class, () -> {
            BigDecimal price = BigDecimal.valueOf(1000);
            BigDecimal amount = BigDecimal.TEN;
            short direction = Constants.EXCHANGE_ORDER.DIRECTION.BUY;
            AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(MemberConstants.IDs.SAJAD).type((short) 1).direction(direction)
                    .symbol(ExchangeCoinConstants.SYMBOLS.SHIB_ETH)
                    .build();
            GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        });
        Assertions.assertEquals(testProjectBusinessException.getMessage(), Constants.EXCEPTION_KEYS.WALLET_NOT_FOUND);
    }
}
