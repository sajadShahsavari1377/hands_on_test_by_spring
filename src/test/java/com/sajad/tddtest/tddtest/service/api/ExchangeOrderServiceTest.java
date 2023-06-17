package com.sajad.tddtest.tddtest.service.api;

import org.junit.jupiter.api.Test;

public interface ExchangeOrderServiceTest {
    @Test
    void addCorrectOrderBuyLimitOrder();

    @Test
    void addNotEnoughBalanceOrderBuyLimitOrder();

    @Test
    void addWalletNotFoundOrderBuyLimitOrder();
}
