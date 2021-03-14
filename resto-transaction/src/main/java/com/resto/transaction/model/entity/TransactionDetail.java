package com.resto.transaction.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="transaction_details")
public class TransactionDetail {

    @Id
    @SequenceGenerator(name = "seq_transaction_detail", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_transaction_detail")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String foodName;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @Column
    private Double totalPrice;

    @ManyToOne
    @JoinColumn
    private Transaction transaction;


}
