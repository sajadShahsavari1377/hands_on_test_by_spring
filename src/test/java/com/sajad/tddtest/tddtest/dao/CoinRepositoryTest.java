package com.sajad.tddtest.tddtest.dao;


import com.sajad.tddtest.tddtest.model.entity.Coin;
import com.sajad.tddtest.tddtest.utility.CoinConstants;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CoinRepositoryTest {

    @Autowired
    private CoinRepository coinRepository;
    List<Coin> coinsToBeInserted;
    Coin bitcoin;
    Coin semiBitCoin;
    Coin etherium;
    Coin shiba;


    @BeforeEach
    public void setup() {
          bitcoin = Coin.builder().name(CoinConstants.NAMES.BTC).displayName(CoinConstants.DISPLAY_NAME.BTC)
                .build();
        semiBitCoin = Coin.builder().name(CoinConstants.NAMES.BTC_LIKE).displayName(CoinConstants.DISPLAY_NAME.BTC_LIKE)
                .build();
          etherium = Coin.builder().name(CoinConstants.NAMES.ETH).displayName(CoinConstants.DISPLAY_NAME.ETH)
                .build();
          shiba = Coin.builder().name(CoinConstants.NAMES.SHIB).displayName(CoinConstants.DISPLAY_NAME.SHIB)
                .build();
        coinsToBeInserted = List.of(bitcoin,etherium,shiba,semiBitCoin);
        coinRepository.saveAll(coinsToBeInserted);
    }

    @Test
    public void testFindAll() {
        List<Coin> foundAllCoins = coinRepository.findAll();
        Assertions.assertIterableEquals(coinsToBeInserted,foundAllCoins);
    }

    @Test
    public void testExistingCoinByName() {
        Optional<Coin> foundCoinByName = coinRepository.findByName(CoinConstants.NAMES.BTC);
        Assertions.assertTrue(foundCoinByName.isPresent());
    }

    @Test
    public void testFindByNameEquality() {
        Optional<Coin> foundCoinByNameOptional = coinRepository.findByName(CoinConstants.NAMES.BTC);
        Assertions.assertEquals(bitcoin,foundCoinByNameOptional.get());
    }

    @Test
    public void testNonExistingCoinByName() {
        Optional<Coin> foundCoinByName = coinRepository.findByName("alakiName");
        Assertions.assertFalse(foundCoinByName.isPresent());
    }

    @Test
    public void testFindAllByDisplayName() {
        List<Coin> coins=coinRepository.findAllByDisplayName(CoinConstants.DISPLAY_NAME.BTC);
        Assertions.assertIterableEquals(List.of(bitcoin,semiBitCoin),coins);
    }





}
