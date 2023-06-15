package com.sajad.tddtest.tddtest.utility.mockers.service;

import com.sajad.tddtest.tddtest.dao.ExchangeCoinRepository;
import com.sajad.tddtest.tddtest.service.api.ExchangeCoinService;
import com.sajad.tddtest.tddtest.utility.mockers.Mocker;
import com.sajad.tddtest.tddtest.utility.mockers.dao.ExchangeCoinRepositoryMocker;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ExchangeCoinServiceMocker implements Mocker<ExchangeCoinService> {


    @Override
    public ExchangeCoinService getMock() {
        ExchangeCoinService exchangeCoinService = Mockito.mock(ExchangeCoinService.class);
        ExchangeCoinRepository exchangeCoinRepository = new ExchangeCoinRepositoryMocker().getMock();
        when(exchangeCoinService.findByCoinSymbol(anyString())).thenAnswer(invocationOnMock -> {
            String coinSymbol = invocationOnMock.getArgument(0, String.class);
            return exchangeCoinRepository.findByCoinSymbol(coinSymbol);
        });
        when(exchangeCoinService.findBySymbol(anyString())).thenAnswer(invocationOnMock -> {
            String symbol = invocationOnMock.getArgument(0, String.class);
            return exchangeCoinRepository.findBySymbol(symbol);
        });
        when(exchangeCoinService.findByBaseSymbol(anyString())).thenAnswer(invocationOnMock -> {
            String baseSymbol = invocationOnMock.getArgument(0, String.class);
            return exchangeCoinRepository.findByBaseSymbol(baseSymbol);
        });

        return exchangeCoinService;
    }
}
