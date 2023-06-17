package com.sajad.tddtest.tddtest.service.impl;

import com.sajad.tddtest.tddtest.dao.ExchangeCoinRepository;
import com.sajad.tddtest.tddtest.model.dto.GeneralExchangeCoinInfo;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;
import com.sajad.tddtest.tddtest.service.api.ExchangeCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeCoinServiceImpl implements ExchangeCoinService {
    private final ExchangeCoinRepository exchangeCoinRepository;

    @Override
    public ExchangeCoin save(ExchangeCoin exchangeCoin) {
        return exchangeCoinRepository.save(exchangeCoin);
    }

    @Override
    public void saveAll(List<ExchangeCoin> exchangeCoins) {
        exchangeCoinRepository.saveAll(exchangeCoins);
    }

    @Override
    public List<ExchangeCoin> findAll() {
        return exchangeCoinRepository.findAll();
    }

    @Override
    public GeneralExchangeCoinInfo findBySymbol(String symbol) {
        return null;
    }

    @Override
    public GeneralExchangeCoinInfo findByCoinSymbol(String coinSymbol) {
        return null;
    }

    @Override
    public GeneralExchangeCoinInfo findByBaseSymbol(String coinSymbol) {
        return null;
    }

    @Override
    public void deleteAll() {
        exchangeCoinRepository.deleteAll();
    }
}
