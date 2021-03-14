package com.resto.transaction.service;

import com.resto.transaction.model.request.TransactionDetailRequest;
import com.resto.transaction.model.response.TransactionDetailDto;

import java.util.List;

public interface TransactionDetailService {
    TransactionDetailDto saveTransactionDetail(TransactionDetailRequest request);
    TransactionDetailDto getTransactionDetailById (Long transactionId);
    List<TransactionDetailDto> findAll();
}
