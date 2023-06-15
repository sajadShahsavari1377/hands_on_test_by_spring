package com.sajad.tddtest.tddtest.service.api;


import com.sajad.tddtest.tddtest.model.dto.AddOrderDto;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;

public interface ExchangeOrderService {

    GeneralOrderInfo addOrder(AddOrderDto addOrderDto);
    GeneralOrderInfo findGeneralInfoById(Long orderId);

}
