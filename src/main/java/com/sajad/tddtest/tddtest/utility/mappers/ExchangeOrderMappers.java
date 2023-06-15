package com.sajad.tddtest.tddtest.utility.mappers;

import com.sajad.tddtest.tddtest.model.dto.AddOrderDto;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;
import com.sajad.tddtest.tddtest.model.entity.ExchangeOrder;
import com.sajad.tddtest.tddtest.utility.constants.Constants;

//it's better to use mapper libraries like mapstruct
public class ExchangeOrderMappers {
    public static ExchangeOrder mapAddExchangeOrderDtoToExchangeOrder(AddOrderDto addOrderDto) {
        String[] splitSymbol = addOrderDto.getSymbol().split(Constants.EXCHANGE_COIN.SYMBOL_SEPARATOR);
        return ExchangeOrder.builder().symbol(addOrderDto.getSymbol()).coinSymbol(splitSymbol[0]).baseSymbol(splitSymbol[1])
                .memberId(addOrderDto.getMemberId()).amount(addOrderDto.getAmount())
                .direction(addOrderDto.getDirection())
                .type(addOrderDto.getType())
                .price(addOrderDto.getPrice())
                .build();

    }

    public static GeneralOrderInfo mapExchangeOrderToGeneralOrderInfo(ExchangeOrder exchangeOrder) {
        return GeneralOrderInfo.builder().id(exchangeOrder.getId())
                .amount(exchangeOrder.getAmount())
                .trackingCode(exchangeOrder.getTrackingCode())
                .direction(exchangeOrder.getDirection())
                .price(exchangeOrder.getPrice()).build();
    }
}
