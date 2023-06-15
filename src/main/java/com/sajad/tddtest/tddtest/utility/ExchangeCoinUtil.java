package com.sajad.tddtest.tddtest.utility;

import com.sajad.tddtest.tddtest.utility.constants.Constants;

public class ExchangeCoinUtil {
    public static String getCoinSymbol(String symbol) {
        return symbol.split(Constants.EXCHANGE_COIN.SYMBOL_SEPARATOR)[0];
    }
    public static String getBaseSymbol(String symbol) {
        return symbol.split(Constants.EXCHANGE_COIN.SYMBOL_SEPARATOR)[1];
    }
}
