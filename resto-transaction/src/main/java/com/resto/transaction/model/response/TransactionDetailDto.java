package com.resto.transaction.model.response;

import com.resto.transaction.model.entity.Transaction;
import lombok.Data;


@Data
public class TransactionDetailDto {
    private Long id;
    private String foodName;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    private Long transaction;
}
