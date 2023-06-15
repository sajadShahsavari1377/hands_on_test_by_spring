package com.sajad.tddtest.tddtest.dao;

import com.sajad.tddtest.tddtest.base.BaseRepository;
import com.sajad.tddtest.tddtest.model.entity.Coin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinRepository extends BaseRepository<Coin> {

    Optional<Coin> findByName(String name);

    List<Coin> findAllByDisplayName(String bitcoinDisplayName);
}
