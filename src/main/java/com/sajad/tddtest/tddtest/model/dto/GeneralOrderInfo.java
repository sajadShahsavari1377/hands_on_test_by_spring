package com.sajad.tddtest.tddtest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor@AllArgsConstructor@Data
@Builder
public class GeneralOrderInfo {
    private Long id;
    private String trackingCode;
    private BigDecimal amount;
    private BigDecimal price;
    private short type;
    private short direction;
}
