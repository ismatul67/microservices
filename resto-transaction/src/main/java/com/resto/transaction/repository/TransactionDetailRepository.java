package com.resto.transaction.repository;

import com.resto.transaction.model.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository  extends JpaRepository<TransactionDetail, Long> {

}
