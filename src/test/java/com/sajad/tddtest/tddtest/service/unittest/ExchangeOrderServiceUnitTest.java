package com.sajad.tddtest.tddtest.service.unittest;

import com.sajad.tddtest.tddtest.dao.ExchangeOrderRepository;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;
import com.sajad.tddtest.tddtest.model.dto.SelectedWalletMarketDto;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.model.exception.TestProjectBusinessException;
import com.sajad.tddtest.tddtest.service.AbstractExchangeOrderServiceTest;
import com.sajad.tddtest.tddtest.service.api.ExchangeCoinService;
import com.sajad.tddtest.tddtest.service.api.ExchangeOrderService;
import com.sajad.tddtest.tddtest.service.api.ExchangeOrderServiceTest;
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

@ExtendWith(SpringExtension.class)
public class ExchangeOrderServiceUnitTest extends AbstractExchangeOrderServiceTest {


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
