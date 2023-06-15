package com.sajad.tddtest.tddtest.utility.constants;

public interface Constants {
    interface EXCHANGE_ORDER{
        interface DIRECTION {
            short SELL = 0;
            short BUY = 1;
        }
        interface TYPE{
            short MARKET_PRICE = 0;
            short LIMIT_PRICE = 1;
        }
    }

    interface EXCHANGE_COIN{
        String SYMBOL_SEPARATOR = "/";
    }


    interface EXCEPTION_KEYS {
        String WALLET_NOT_FOUND = "WALLET_NOT_FOUND";
        String NOT_ENOUGH_BALANCE = "NOT_ENOUGH_BALANCE";
        String NOT_ENOUGH_FROZEN_BALANCE = "NOT_ENOUGH_FROZEN_BALANCE";
    }


}
