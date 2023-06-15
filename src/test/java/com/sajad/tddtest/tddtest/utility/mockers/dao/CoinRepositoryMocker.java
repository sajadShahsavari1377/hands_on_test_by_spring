package com.sajad.tddtest.tddtest.utility.mockers.dao;


import com.sajad.tddtest.tddtest.dao.CoinRepository;
import com.sajad.tddtest.tddtest.model.entity.Coin;
import com.sajad.tddtest.tddtest.utility.CoinConstants;
import com.sajad.tddtest.tddtest.utility.mockers.Mocker;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static com.sajad.tddtest.tddtest.utility.CoinConstants.*;

public class CoinRepositoryMocker  implements Mocker<CoinRepository> {
    private List<Coin> coinsToBeInserted;
    private Coin bitcoin;
    private Coin semiBitCoin;
    private Coin etherium;
    private Coin shiba;

    public CoinRepositoryMocker() {
        bitcoin = Coin.builder().id(1l).name(NAMES.BTC).displayName(DISPLAY_NAME.BTC)
                .unit(UNITS.BTC)
                .build();
        semiBitCoin = Coin.builder().id(2l).name(NAMES.BTC_LIKE).displayName(DISPLAY_NAME.BTC_LIKE)
                .unit(UNITS.BTC_LIKE)
                .build();
        etherium = Coin.builder().id(3l).name(NAMES.ETH).displayName(DISPLAY_NAME.ETH)
                .unit(UNITS.ETH)
                .build();
        shiba = Coin.builder().id(4l).name(NAMES.SHIB).displayName(DISPLAY_NAME.SHIB)
                .unit(UNITS.SHIB)
                .build();
        coinsToBeInserted = List.of(bitcoin,etherium,shiba,semiBitCoin);
    }

    @Override
    public  CoinRepository getMock() {
        CoinRepository coinRepository = Mockito.mock(CoinRepository.class);
        when(coinRepository.findAll()).thenReturn(coinsToBeInserted);
        when(coinRepository.findByName(anyString())).thenAnswer(invocationOnMock -> {
            String coinName = (String) invocationOnMock.getArgument(0);
            return coinsToBeInserted.stream().filter(coin -> coin.getName().equals(coinName)).findAny();
        });
        when(coinRepository.findAllByDisplayName(anyString())).thenAnswer(invocationOnMock -> {
            String displayName = (String) invocationOnMock.getArgument(0);
            return coinsToBeInserted.stream().filter(coin -> coin.getDisplayName().equals(displayName)).collect(Collectors.toList());
        });
        when(coinRepository.findById(anyLong())).thenAnswer(invocationOnMock -> {
            Long id = (Long) invocationOnMock.getArgument(0);
            Optional<Coin> foundCoinById = coinsToBeInserted.stream().filter(coin -> coin.getId() == id).findAny();
            return foundCoinById;
        });
        return coinRepository;
    }


    public List<Coin> getAllCoins() {
        return coinsToBeInserted;
    }

}
