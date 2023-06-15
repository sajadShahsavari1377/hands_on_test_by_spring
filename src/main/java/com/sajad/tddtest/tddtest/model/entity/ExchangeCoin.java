package com.sajad.tddtest.tddtest.model.entity;

import com.sajad.tddtest.tddtest.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uq_symbol",columnNames = {"symbol"})})
@NoArgsConstructor@AllArgsConstructor@SuperBuilder@Data
public class ExchangeCoin extends BaseEntity {
    private String symbol;
    private String coinSymbol;
    private String baseSymbol;
    @Column(scale = 18, precision = 36)
    private BigDecimal fee;

}
