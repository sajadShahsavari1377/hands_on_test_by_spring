package com.sajad.tddtest.tddtest.model.dto;

import com.sajad.tddtest.tddtest.model.entity.MemberWallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class SelectedWalletMarketDto {
    private MemberWallet memberWallet;
    private String marketSymbol;
}
