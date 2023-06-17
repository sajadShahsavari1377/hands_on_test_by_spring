package com.sajad.tddtest.tddtest.service.api;

import com.sajad.tddtest.tddtest.model.dto.GeneralExchangeCoinInfo;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;

import java.util.List;

public interface ExchangeCoinService {
    ExchangeCoin save(ExchangeCoin exchangeCoin);
    void saveAll(List<ExchangeCoin> exchangeCoins);

    List<ExchangeCoin> findAll();
    GeneralExchangeCoinInfo findBySymbol(String symbol);
    GeneralExchangeCoinInfo findByCoinSymbol(String coinSymbol);
    GeneralExchangeCoinInfo findByBaseSymbol(String coinSymbol);

    void deleteAll();
}
