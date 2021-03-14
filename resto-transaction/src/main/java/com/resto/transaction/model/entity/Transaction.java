package com.resto.transaction.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @SequenceGenerator(name = "seq_transaction", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_transaction")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String customerName;

    @Column
    private Date date;

    @Column
    private String cashier;

    @OneToMany(
            mappedBy = "transaction",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TransactionDetail> trxDetailList;

}
