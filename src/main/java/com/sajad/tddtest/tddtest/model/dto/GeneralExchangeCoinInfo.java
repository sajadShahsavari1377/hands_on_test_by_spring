package com.sajad.tddtest.tddtest.model.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor@AllArgsConstructor@Builder
public class GeneralExchangeCoinInfo {
    private String symbol;
    private String coinSymbol;
    private String baseSymbol;
    private BigDecimal fee;

}
