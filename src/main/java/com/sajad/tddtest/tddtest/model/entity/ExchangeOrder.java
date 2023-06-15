package com.sajad.tddtest.tddtest.model.entity;

import com.sajad.tddtest.tddtest.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class ExchangeOrder extends BaseEntity {
    private String symbol;
    private Long memberId;
    @Column(length = 37)
    private String trackingCode;
    private BigDecimal amount;
    private BigDecimal price;
    //    0 = buy and 1 = sell
    private short direction;
    //    0 = market_price and 1= limit
    private short type;
    private String coinSymbol;
    private String baseSymbol;
}
