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
@Data
@SuperBuilder
public class MemberWallet extends BaseEntity {
    private String symbol;
    private Long memberId;
    @Column(scale = 18,precision = 36)
    private BigDecimal balance;
    @Column(scale = 18,precision = 36)
    private BigDecimal frozenBalance;
}
