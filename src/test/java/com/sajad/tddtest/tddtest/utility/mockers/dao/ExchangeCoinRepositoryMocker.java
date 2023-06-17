package com.sajad.tddtest.tddtest.utility.mockers.dao;

import com.sajad.tddtest.tddtest.dao.CoinRepository;
import com.sajad.tddtest.tddtest.dao.ExchangeCoinRepository;
import com.sajad.tddtest.tddtest.model.entity.Coin;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;
import com.sajad.tddtest.tddtest.utility.mockers.Mocker;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static com.sajad.tddtest.tddtest.utility.CoinConstants.*;
import static com.sajad.tddtest.tddtest.utility.ExchangeCoinConstants.*;

public class ExchangeCoinRepositoryMocker implements Mocker<ExchangeCoinRepository> {

    private List<ExchangeCoin> exchangeCoins;
    private ExchangeCoin ethBtc;
    private ExchangeCoin shibBtc;
    private ExchangeCoin btcLikeBtc;
    private ExchangeCoin shibEth;
    private ExchangeCoin btcLikeEth;

    public ExchangeCoinRepositoryMocker() {
        ethBtc = ExchangeCoin.builder().id(1l).symbol(SYMBOLS.ETH_BTC).coinSymbol(UNITS.ETH)
                .baseSymbol(UNITS.BTC).fee(BigDecimal.valueOf(0.1))
                .build();
        shibBtc = ExchangeCoin.builder().id(2l).symbol(SYMBOLS.SHIB_BTC).coinSymbol(UNITS.SHIB)
                .baseSymbol(UNITS.BTC).fee(BigDecimal.valueOf(0.2))
                .build();
        btcLikeBtc = ExchangeCoin.builder().id(3l).symbol(SYMBOLS.BTC_LIKE_BTC).coinSymbol(UNITS.BTC_LIKE)
                .baseSymbol(UNITS.BTC).fee(BigDecimal.valueOf(0.3))
                .build();
        shibEth = ExchangeCoin.builder().id(4l).symbol(SYMBOLS.SHIB_ETH).coinSymbol(UNITS.SHIB)
                .baseSymbol(UNITS.ETH).fee(BigDecimal.valueOf(0.4))
                .build();
        btcLikeEth = ExchangeCoin.builder().id(5l).symbol(SYMBOLS.BTC_LIKE_ETH).coinSymbol(UNITS.BTC_LIKE)
                .baseSymbol(UNITS.ETH).fee(BigDecimal.valueOf(0.5))
                .build();
        exchangeCoins = List.of(ethBtc,shibBtc,btcLikeBtc,shibEth,btcLikeEth);
    }

    @Override
    public  ExchangeCoinRepository getMock() {
        ExchangeCoinRepository exchangeCoinRepository = Mockito.mock(ExchangeCoinRepository.class);
        when(exchangeCoinRepository.findAll()).thenReturn(exchangeCoins);
        when(exchangeCoinRepository.findBySymbol(anyString())).thenAnswer(invocationOnMock -> {
            String symbol = (String) invocationOnMock.getArgument(0);
            return exchangeCoins.stream().filter(exchangeCoin -> exchangeCoin.getSymbol().equals(symbol)).findAny();
        });
        when(exchangeCoinRepository.findByCoinSymbol(anyString())).thenAnswer(invocationOnMock -> {
            String coinSymbol = (String) invocationOnMock.getArgument(0);
            return exchangeCoins.stream().filter(exchangeCoin -> exchangeCoin.getCoinSymbol().equals(coinSymbol)).collect(Collectors.toList());
        });
        when(exchangeCoinRepository.findByBaseSymbol(anyString())).thenAnswer(invocationOnMock -> {
            String baseSymbol = (String) invocationOnMock.getArgument(0);
            return exchangeCoins.stream().filter(exchangeCoin -> exchangeCoin.getBaseSymbol().equals(baseSymbol)).collect(Collectors.toList());
        });
        when(exchangeCoinRepository.findAll()).thenReturn(exchangeCoins);
        return exchangeCoinRepository;
    }


    public List<ExchangeCoin> getAllExchangeCoins() {
        return exchangeCoins;
    }
}
