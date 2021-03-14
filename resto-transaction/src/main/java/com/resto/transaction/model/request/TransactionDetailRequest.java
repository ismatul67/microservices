package com.resto.transaction.model.request;

import lombok.Data;

@Data
public class TransactionDetailRequest {
    private Integer foodId;

    private Integer quantity;

    private Long transactionId;
}
