package com.sajad.tddtest.tddtest.service.impl;

import com.sajad.tddtest.tddtest.dao.ExchangeOrderRepository;
import com.sajad.tddtest.tddtest.model.dto.AddOrderDto;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;
import com.sajad.tddtest.tddtest.model.entity.ExchangeOrder;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.model.exception.TestProjectBusinessException;
import com.sajad.tddtest.tddtest.service.api.ExchangeCoinService;
import com.sajad.tddtest.tddtest.service.api.ExchangeOrderService;
import com.sajad.tddtest.tddtest.service.api.MemberWalletService;
import com.sajad.tddtest.tddtest.utility.ExchangeCoinUtil;
import com.sajad.tddtest.tddtest.utility.constants.Constants;
import com.sajad.tddtest.tddtest.utility.mappers.ExchangeOrderMappers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static com.sajad.tddtest.tddtest.utility.constants.Constants.EXCEPTION_KEYS.NOT_ENOUGH_BALANCE;
import static com.sajad.tddtest.tddtest.utility.constants.Constants.EXCEPTION_KEYS.WALLET_NOT_FOUND;
import static com.sajad.tddtest.tddtest.utility.constants.Constants.EXCHANGE_ORDER.DIRECTION.*;
import static com.sajad.tddtest.tddtest.utility.constants.Constants.EXCHANGE_ORDER.TYPE.LIMIT_PRICE;

@Service
@RequiredArgsConstructor
public class ExchangeOrderServiceImpl implements ExchangeOrderService {
    private final MemberWalletService memberWalletService;
    private final ExchangeCoinService exchangeCoinService;
    private final ExchangeOrderRepository exchangeOrderRepository;

    @Override
    @Transactional
    public GeneralOrderInfo addOrder(AddOrderDto addOrderDto) {
        MemberWallet toDecreaseWallet = getDecreaseWallet(addOrderDto).orElseThrow(() -> new TestProjectBusinessException(WALLET_NOT_FOUND));
        BigDecimal toDecreaseAmount = calculateDecreaseAmount(addOrderDto);
        if (!hasEnoughBalance(toDecreaseWallet,toDecreaseAmount)) {
            throw new TestProjectBusinessException(NOT_ENOUGH_BALANCE);
        }
        int decreaseBalanceResult=memberWalletService.decreaseBalance(toDecreaseWallet.getId(),toDecreaseAmount);
        if (decreaseBalanceResult == 0) {
            throw new TestProjectBusinessException(NOT_ENOUGH_BALANCE);
        }
        memberWalletService.increaseFrozenBalance(toDecreaseWallet.getId(),toDecreaseAmount);
        ExchangeOrder exchangeOrder = ExchangeOrderMappers.mapAddExchangeOrderDtoToExchangeOrder(addOrderDto);
        exchangeOrder.setTrackingCode(UUID.randomUUID().toString());
        exchangeOrderRepository.save(exchangeOrder);
        return ExchangeOrderMappers.mapExchangeOrderToGeneralOrderInfo(exchangeOrder);
    }

    private boolean hasEnoughBalance(MemberWallet toDecreaseWallet, BigDecimal toDecreaseAmount) {
        if (toDecreaseWallet.getBalance().compareTo(toDecreaseAmount) < 0) {
            return false;
        }else {
            return true;
        }
    }

    private BigDecimal calculateDecreaseAmount(AddOrderDto addOrderDto) {
        if (LIMIT_PRICE == addOrderDto.getType()) {
            return addOrderDto.getAmount().multiply(addOrderDto.getPrice());
        }else {
                return addOrderDto.getAmount();
        }
    }

    private Optional<MemberWallet> getDecreaseWallet(AddOrderDto addOrderDto) {
        String toDecreaseSymbol = null;
        if (BUY == addOrderDto.getDirection()) {
            toDecreaseSymbol=ExchangeCoinUtil.getBaseSymbol(addOrderDto.getSymbol());
        }else {
            toDecreaseSymbol = ExchangeCoinUtil.getCoinSymbol(addOrderDto.getSymbol());
        }
        return memberWalletService.findByMemberIdAndSymbol(addOrderDto.getMemberId(), toDecreaseSymbol);
    }


    @Override
    public GeneralOrderInfo findGeneralInfoById(Long orderId) {
        return null;
    }
}
