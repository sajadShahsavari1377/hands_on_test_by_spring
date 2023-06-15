package com.sajad.tddtest.tddtest.utility.mockers.dao;

import com.sajad.tddtest.tddtest.dao.ExchangeOrderRepository;
import com.sajad.tddtest.tddtest.model.entity.ExchangeOrder;
import com.sajad.tddtest.tddtest.utility.mockers.Mocker;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ExchangeOrderRepositoryMocker implements Mocker<ExchangeOrderRepository> {
    private Map<Long, ExchangeOrder> exchangeOrderMap = new ConcurrentHashMap<>();
    private AtomicLong lastId=new AtomicLong(0);


    @Override
    public ExchangeOrderRepository getMock() {
        ExchangeOrderRepository exchangeOrderRepository = Mockito.mock(ExchangeOrderRepository.class);
        when(exchangeOrderRepository.findById(anyLong())).thenAnswer(invocationOnMock ->
             Optional.ofNullable(exchangeOrderMap.get(invocationOnMock.getArgument(0, Long.class)))
        );
        when(exchangeOrderRepository.save(any(ExchangeOrder.class))).thenAnswer(invocationOnMock -> {
            ExchangeOrder inputExchangeOrder = invocationOnMock.getArgument(0, ExchangeOrder.class);
            inputExchangeOrder.setId(lastId.getAndAdd(1));
            exchangeOrderMap.put(inputExchangeOrder.getId(), inputExchangeOrder);
            return inputExchangeOrder;
        });
        return exchangeOrderRepository;
    }
}
