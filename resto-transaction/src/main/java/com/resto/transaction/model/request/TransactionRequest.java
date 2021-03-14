package com.resto.transaction.model.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class TransactionRequest {
    private String customerName;
    private String cashier;
}
