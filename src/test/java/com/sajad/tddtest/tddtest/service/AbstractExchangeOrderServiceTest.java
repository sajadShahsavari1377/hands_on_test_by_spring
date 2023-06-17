package com.sajad.tddtest.tddtest.service;

import com.sajad.tddtest.tddtest.model.dto.AddOrderDto;
import com.sajad.tddtest.tddtest.model.dto.GeneralOrderInfo;
import com.sajad.tddtest.tddtest.model.dto.SelectedWalletMarketDto;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;
import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import com.sajad.tddtest.tddtest.model.exception.TestProjectBusinessException;
import com.sajad.tddtest.tddtest.service.api.*;
import com.sajad.tddtest.tddtest.utility.ExchangeCoinConstants;
import com.sajad.tddtest.tddtest.utility.constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractExchangeOrderServiceTest implements ExchangeOrderServiceTest {
    protected ExchangeOrderService exchangeOrderService;
    protected ExchangeCoinService exchangeCoinService;
    protected MemberWalletService memberWalletService;
    protected MemberService memberService;


    @Override
    @Test
    public void addCorrectOrderBuyLimitOrder() {
        BigDecimal price = BigDecimal.valueOf(2l);
        BigDecimal amount = BigDecimal.valueOf(3l);
        BigDecimal decreaseAmount = price.multiply(amount);
        short direction = Constants.EXCHANGE_ORDER.DIRECTION.BUY;
        short type = Constants.EXCHANGE_ORDER.TYPE.LIMIT_PRICE;
        SelectedWalletMarketDto selectedWalletMarketDto = getSelectedWalletToTest(direction,decreaseAmount, true);
        if (Objects.isNull(selectedWalletMarketDto.getMarketSymbol()) || Objects.isNull(selectedWalletMarketDto.getMemberWallet())) {
            throw new RuntimeException("no wallet with enough balance to test correct scenario");
        }
        BigDecimal beforeOperationBalance = selectedWalletMarketDto.getMemberWallet().getBalance();
        BigDecimal beforeOperationFozenBalance = selectedWalletMarketDto.getMemberWallet().getFrozenBalance();
        AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(selectedWalletMarketDto.getMemberWallet().getMemberId()).type(type).direction(direction)
                .symbol(selectedWalletMarketDto.getMarketSymbol())
                .build();
        GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        Assertions.assertNotNull(generalOrderInfo);
        Assertions.assertNotNull(generalOrderInfo.getId());
        MemberWallet memberWalletAfterOrder = memberWalletService.findById(selectedWalletMarketDto.getMemberWallet().getId()).get();
        Assertions.assertEquals(0, memberWalletAfterOrder.getBalance().compareTo(beforeOperationBalance.subtract(price.multiply(amount))));
        Assertions.assertEquals(0, memberWalletAfterOrder.getFrozenBalance().compareTo(beforeOperationFozenBalance.add(price.multiply(amount))));
    }

    @Override
    public void addNotEnoughBalanceOrderBuyLimitOrder() {
        short direction = Constants.EXCHANGE_ORDER.DIRECTION.BUY;
        short type = Constants.EXCHANGE_ORDER.TYPE.LIMIT_PRICE;
        BigDecimal price = BigDecimal.valueOf(2000l);
        BigDecimal amount = BigDecimal.valueOf(30l);
        BigDecimal decreaseAmount = price.multiply(amount);
        SelectedWalletMarketDto selectedWalletToTest = getSelectedWalletToTest(direction, decreaseAmount, false);
        if (Objects.isNull(selectedWalletToTest.getMarketSymbol()) || Objects.isNull(selectedWalletToTest.getMemberWallet())) {
            throw new RuntimeException("no wallet to test not enough balance scenario");
        }
        BigDecimal beforeOperationBalance = selectedWalletToTest.getMemberWallet().getBalance();
        BigDecimal beforeOperationFozenBalance = selectedWalletToTest.getMemberWallet().getFrozenBalance();
        TestProjectBusinessException testProjectBusinessException = Assertions.assertThrows(TestProjectBusinessException.class, () -> {
            AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(selectedWalletToTest.getMemberWallet().getMemberId()).type(type).direction(direction)
                    .symbol(ExchangeCoinConstants.SYMBOLS.ETH_BTC)
                    .build();
            GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        });
        Assertions.assertEquals(testProjectBusinessException.getMessage(), Constants.EXCEPTION_KEYS.NOT_ENOUGH_BALANCE);
        MemberWallet memberWalletAfterOrder = memberWalletService.findById(selectedWalletToTest.getMemberWallet().getId()).get();
        Assertions.assertEquals(0, beforeOperationBalance.compareTo(memberWalletAfterOrder.getBalance()));
        Assertions.assertEquals(0, beforeOperationFozenBalance.compareTo(memberWalletAfterOrder.getFrozenBalance()));
    }

    @Override
    public void addWalletNotFoundOrderBuyLimitOrder() {
        TestProjectBusinessException testProjectBusinessException = Assertions.assertThrows(TestProjectBusinessException.class, () -> {
            BigDecimal price = BigDecimal.valueOf(1000);
            BigDecimal amount = BigDecimal.TEN;
            short direction = Constants.EXCHANGE_ORDER.DIRECTION.BUY;
            short type = Constants.EXCHANGE_ORDER.TYPE.LIMIT_PRICE;
            String selectedSymbol = null;
            Long selectedMemberId = null;
            for (ExchangeCoin exchangeCoin : exchangeCoinService.findAll()) {
                String targetSymbol = exchangeCoin.getBaseSymbol();
                Map<Long, List<MemberWallet>> groupedMemberWalletsByMember = memberWalletService.findAll().stream().collect(Collectors.groupingBy(MemberWallet::getMemberId));
                for (Map.Entry<Long, List<MemberWallet>> memberWalletEntry : groupedMemberWalletsByMember.entrySet()) {
                    Optional<MemberWallet> anyWalletByTargetSymbol = memberWalletEntry.getValue().stream().filter(memberWallet -> memberWallet.getSymbol().equalsIgnoreCase(targetSymbol))
                            .findAny();
                    if (anyWalletByTargetSymbol.isPresent()) {
                        continue;
                    }else {
                        selectedSymbol = exchangeCoin.getSymbol();
                        selectedMemberId = memberWalletEntry.getKey();
                        break;
                    }
                }
                if (Objects.isNull(selectedSymbol) && Objects.isNull(selectedMemberId)) {
                    break;
                }
            }
            if (Objects.isNull(selectedSymbol) || Objects.isNull(selectedMemberId)) {
                throw new RuntimeException("no wallet found to test wallet not found scenario");
            }
            AddOrderDto addOrderDto = AddOrderDto.builder().amount(amount).price(price).memberId(selectedMemberId).type(type).direction(direction)
                    .symbol(selectedSymbol)
                    .build();
            GeneralOrderInfo generalOrderInfo = exchangeOrderService.addOrder(addOrderDto);
        });
        Assertions.assertEquals(testProjectBusinessException.getMessage(), Constants.EXCEPTION_KEYS.WALLET_NOT_FOUND);
    }


    private SelectedWalletMarketDto getSelectedWalletToTest(short desiredOrderDirection, BigDecimal decreaseAmount, boolean hasEnoughBalance) {
        String selectedMarket=null;
        MemberWallet selectedWallet=null;
        List<MemberWallet> availableWallets = memberWalletService.findAll().stream()
                .filter(memberWallet -> hasEnoughBalance ? memberWallet.getBalance().compareTo(decreaseAmount) >= 0 : memberWallet.getBalance().compareTo(decreaseAmount) < 0)
                .collect(Collectors.toList());
        for (MemberWallet availableWallet : availableWallets) {
            List<ExchangeCoin> availableMarkets = getTargetMarkets(desiredOrderDirection, availableWallet.getSymbol());
            if (!availableMarkets.isEmpty()) {
                selectedMarket=availableMarkets.get(0).getSymbol();
                selectedWallet = availableWallet;
                break;
            }
        }
        return new SelectedWalletMarketDto(selectedWallet, selectedMarket);
    }

    private List<ExchangeCoin> getTargetMarkets(short desiredOrderDirection,String desiredUnit) {
        return exchangeCoinService.findAll().stream()
                .filter(exchangeCoin -> desiredOrderDirection == Constants.EXCHANGE_ORDER.DIRECTION.BUY ? exchangeCoin.getBaseSymbol().equalsIgnoreCase(desiredUnit) : exchangeCoin.getCoinSymbol().equalsIgnoreCase(desiredUnit)).collect(Collectors.toList());
    }
}
