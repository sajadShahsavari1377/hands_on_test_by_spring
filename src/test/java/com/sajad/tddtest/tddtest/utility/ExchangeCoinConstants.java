package com.sajad.tddtest.tddtest.utility;

import static com.sajad.tddtest.tddtest.utility.CoinConstants.*;

public interface ExchangeCoinConstants {

    String EXCHANGE_COIN_SEPARATOR = "/";

    interface SYMBOLS {
         String ETH_BTC = UNITS.ETH+EXCHANGE_COIN_SEPARATOR+UNITS.BTC;
         String SHIB_BTC = UNITS.SHIB+EXCHANGE_COIN_SEPARATOR+UNITS.BTC;
         String BTC_LIKE_BTC = UNITS.BTC_LIKE+EXCHANGE_COIN_SEPARATOR+UNITS.BTC;
         String SHIB_ETH = UNITS.SHIB+EXCHANGE_COIN_SEPARATOR+UNITS.ETH;
         String BTC_LIKE_ETH = UNITS.BTC_LIKE+EXCHANGE_COIN_SEPARATOR+UNITS.ETH;
    }
}
