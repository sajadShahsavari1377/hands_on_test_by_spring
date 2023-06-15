package com.sajad.tddtest.tddtest.utility.mockers;

import com.sajad.tddtest.tddtest.dao.CoinRepository;

public interface Mocker<MOCKED_TYPE> {

    MOCKED_TYPE getMock();
}
