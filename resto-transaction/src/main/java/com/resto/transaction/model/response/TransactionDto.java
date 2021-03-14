package com.resto.transaction.model.response;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class TransactionDto {
    private Long id;
    private String customerName;
    private Date date;
    private String cashier;
    private List<TransactionDetailDto> trxDetailList;
}
