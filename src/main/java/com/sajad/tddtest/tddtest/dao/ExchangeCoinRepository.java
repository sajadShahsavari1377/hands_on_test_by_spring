package com.sajad.tddtest.tddtest.dao;

import com.sajad.tddtest.tddtest.base.BaseRepository;
import com.sajad.tddtest.tddtest.model.entity.ExchangeCoin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeCoinRepository extends BaseRepository<ExchangeCoin> {
    Optional<String> findBySymbol(String symbol);

    List<ExchangeCoin> findByCoinSymbol(String coinSymbol);

    List<ExchangeCoin> findByBaseSymbol(String baseSymbol);

}
