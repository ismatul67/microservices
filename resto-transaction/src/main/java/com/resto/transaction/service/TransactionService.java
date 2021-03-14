package com.resto.transaction.service;

import com.resto.transaction.model.request.TransactionRequest;
import com.resto.transaction.model.response.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto saveTransaction(TransactionRequest request);
    TransactionDto getTransactionById (Long transactionId);
    List<TransactionDto> findAll();

}
