package com.resto.transaction.service.impl;

import com.resto.transaction.model.entity.Transaction;
import com.resto.transaction.model.entity.TransactionDetail;
import com.resto.transaction.model.request.TransactionRequest;
import com.resto.transaction.model.response.TransactionDetailDto;
import com.resto.transaction.model.response.TransactionDto;
import com.resto.transaction.repository.TransactionRepository;
import com.resto.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public TransactionDto saveTransaction(TransactionRequest request) {
        log.info("save transaction {}", request);
        Transaction transaction = new Transaction();
        List<TransactionDetailDto> transactionDetailDtos = new ArrayList<>();
        transaction.setCashier(request.getCashier());
        transaction.setCustomerName(request.getCustomerName());
        transaction.setDate(new Date());
        Transaction response = transactionRepository.save(transaction);

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setId(response.getId());
        transactionDto.setCashier(response.getCashier());
        transactionDto.setCustomerName(response.getCustomerName());

        TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
        for ( TransactionDetail e: response.getTrxDetailList()) {
            transactionDetailDto.setId(e.getId());
            transactionDetailDto.setTransaction(e.getTransaction().getId());
            transactionDetailDto.setFoodName(e.getFoodName());
            transactionDetailDto.setPrice(e.getPrice());
            transactionDetailDto.setTotalPrice(e.getTotalPrice());
            transactionDetailDto.setQuantity(e.getQuantity());
            transactionDetailDtos.add(transactionDetailDto);
        }
        transactionDto.setTrxDetailList(transactionDetailDtos);

        return transactionDto;
    }

    @Override
    public TransactionDto getTransactionById(Long transactionId) {
        log.info("get transaction id {}", transactionId);
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        TransactionDto transactionDto = new TransactionDto();
        List<TransactionDetailDto> transactionDetailDtos = new ArrayList<>();

        if (transaction.isPresent()){
           transactionDto.setId(transaction.get().getId());
           transactionDto.setCashier(transaction.get().getCashier());
           transactionDto.setCustomerName(transaction.get().getCustomerName());

           TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
           for ( TransactionDetail e: transaction.get().getTrxDetailList()) {
               transactionDetailDto.setId(e.getId());
               transactionDetailDto.setTransaction(e.getTransaction().getId());
               transactionDetailDto.setFoodName(e.getFoodName());
               transactionDetailDto.setPrice(e.getPrice());
               transactionDetailDto.setTotalPrice(e.getTotalPrice());
               transactionDetailDto.setQuantity(e.getQuantity());
               transactionDetailDtos.add(transactionDetailDto);
           }
           transactionDto.setTrxDetailList(transactionDetailDtos);
       }
        return  transactionDto;
    }

    @Override
    public List<TransactionDto> findAll() {
        log.info("find transactions start [] ");
        List<Transaction> transactions= transactionRepository.findAll();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        List<TransactionDetailDto> transactionDetailDtos = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setId(transaction.getId());
            transactionDto.setCashier(transaction.getCashier());
            transactionDto.setCustomerName(transaction.getCustomerName());

            TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
            for ( TransactionDetail e: transaction.getTrxDetailList()) {
                transactionDetailDto.setId(e.getId());
                transactionDetailDto.setTransaction(e.getTransaction().getId());
                transactionDetailDto.setFoodName(e.getFoodName());
                transactionDetailDto.setPrice(e.getPrice());
                transactionDetailDto.setTotalPrice(e.getTotalPrice());
                transactionDetailDto.setQuantity(e.getQuantity());
                transactionDetailDtos.add(transactionDetailDto);
            }
            transactionDto.setTrxDetailList(transactionDetailDtos);
            BeanUtils.copyProperties(transaction, transactionDto);
            transactionDtos.add(transactionDto);
        }

        return transactionDtos;
    }
}
