package com.sajad.tddtest.tddtest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder@NoArgsConstructor@AllArgsConstructor@Data
public class AddOrderDto {
    private BigDecimal amount;
    private BigDecimal price;
    private short direction;
    private short type;
    private Long memberId;
    private String symbol;
}
