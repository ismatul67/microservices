package com.resto.transaction.service.impl;

import com.alibaba.fastjson.JSON;
import com.resto.transaction.adaptor.MenuAdaptor;
import com.resto.transaction.model.entity.Transaction;
import com.resto.transaction.model.entity.TransactionDetail;
import com.resto.transaction.model.request.TransactionDetailRequest;
import com.resto.transaction.model.response.MenuDto;
import com.resto.transaction.model.response.TransactionDetailDto;
import com.resto.transaction.model.response.TransactionDto;
import com.resto.transaction.repository.TransactionDetailRepository;
import com.resto.transaction.repository.TransactionRepository;
import com.resto.transaction.service.TransactionDetailService;
import com.resto.transaction.service.TransactionService;
import com.resto.transaction.util.Response;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransactionDetailServiceImpl implements TransactionDetailService {
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private MenuAdaptor menuAdaptor;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionDetailDto saveTransactionDetail(TransactionDetailRequest request) {
        TransactionDetail transactionDetail = new TransactionDetail();
        TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
        ResponseEntity<Response> response= null;
        try {
          response = menuAdaptor.getMenuById(request.getFoodId());
       }catch (FeignException.FeignClientException feignException){
           feignException.printStackTrace();
       }

        MenuDto menuDto = JSON.parseObject(JSON.toJSONString(Objects.requireNonNull(response.getBody()).getData()), MenuDto.class) ;

        if (ObjectUtils.isEmpty(menuDto)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found");
        } else {
            transactionDetail.setFoodName(menuDto.getName());
            transactionDetail.setPrice(menuDto.getPrice().doubleValue());
            transactionDetail.setQuantity(request.getQuantity());
            transactionDetail.setTotalPrice(Double.valueOf(request.getQuantity() * menuDto.getPrice()));
            Transaction transaction = transactionRepository.findById(request.getTransactionId()).get();
            transactionDetail.setTransaction(transaction);

            TransactionDetail trxDetailResponse = transactionDetailRepository.save(transactionDetail);
            transactionDetailDto.setId(trxDetailResponse.getId());
            transactionDetailDto.setFoodName(trxDetailResponse.getFoodName());
            transactionDetailDto.setPrice(trxDetailResponse.getPrice());
            transactionDetailDto.setQuantity(trxDetailResponse.getQuantity());
            transactionDetailDto.setTotalPrice(trxDetailResponse.getTotalPrice());
            transactionDetailDto.setTransaction(trxDetailResponse.getTransaction().getId());
        }
        return transactionDetailDto;
    }

    @Override
    public TransactionDetailDto getTransactionDetailById(Long transactionId) {
       Optional<TransactionDetail> transactionDetail = transactionDetailRepository.findById(transactionId);
       TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
       if (transactionDetail.isPresent()){
           transactionDetailDto.setId(transactionDetail.get().getId());
           transactionDetailDto.setFoodName(transactionDetail.get().getFoodName());
           transactionDetailDto.setPrice(transactionDetail.get().getPrice());
           transactionDetailDto.setQuantity(transactionDetail.get().getQuantity());
           transactionDetailDto.setTotalPrice(transactionDetail.get().getTotalPrice());
           transactionDetailDto.setTransaction(transactionDetail.get().getTransaction().getId());
       }

       return  transactionDetailDto;
    }

    @Override
    public List<TransactionDetailDto> findAll() {
        log.info("find transaction details start [] ");
        List<TransactionDetail> transactionDetails = transactionDetailRepository.findAll();
        List<TransactionDetailDto> transactionDetailDtos = new ArrayList<>();
        for (TransactionDetail transactionDetail : transactionDetails) {
            TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
            transactionDetailDto.setId(transactionDetail.getId());
            transactionDetailDto.setFoodName(transactionDetail.getFoodName());
            transactionDetailDto.setPrice(transactionDetail.getPrice());
            transactionDetailDto.setQuantity(transactionDetail.getQuantity());
            transactionDetailDto.setTotalPrice(transactionDetail.getTotalPrice());
            transactionDetailDto.setTransaction(transactionDetail.getTransaction().getId());
            transactionDetailDtos.add(transactionDetailDto);
        }

        BeanUtils.copyProperties(transactionDetails, transactionDetailDtos);
        return transactionDetailDtos;
    }
}
