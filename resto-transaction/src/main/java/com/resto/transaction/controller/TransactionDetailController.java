package com.resto.transaction.controller;

import com.resto.transaction.model.request.TransactionDetailRequest;
import com.resto.transaction.model.response.TransactionDetailDto;
import com.resto.transaction.model.response.TransactionDto;
import com.resto.transaction.service.TransactionDetailService;
import com.resto.transaction.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-detail")
public class TransactionDetailController {
    @Autowired
    TransactionDetailService transactionDetailService;

    @PostMapping("v1/create")
    ResponseEntity<Response> saveTransactionDetail(@RequestBody TransactionDetailRequest request) {
        TransactionDetailDto transactionDetailDto = transactionDetailService.saveTransactionDetail(request);
        Response response;

        if (!ObjectUtils.isEmpty(transactionDetailDto)){
            response = new Response(transactionDetailDto, "Success insert", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "Failed to insert", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("v1/find-all")
    ResponseEntity<Response> getTransactionDetails() {
        List<TransactionDetailDto> listData = transactionDetailService.findAll();
        Response response;
        if (listData != null || !listData.isEmpty()){
            response = new Response(listData, "Here your data", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "No data found", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("v1/detail/{id}")
    ResponseEntity<Response> getTransactionDetailById(@PathVariable Long id) {
        TransactionDetailDto data = transactionDetailService.getTransactionDetailById(id);
        Response response;
        if (!ObjectUtils.isEmpty(data)){
            response = new Response(data, "Here your data", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "No data found", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
