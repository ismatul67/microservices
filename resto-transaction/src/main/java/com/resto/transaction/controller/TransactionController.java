package com.resto.transaction.controller;

import com.resto.transaction.model.request.TransactionDetailRequest;
import com.resto.transaction.model.request.TransactionRequest;
import com.resto.transaction.model.response.TransactionDto;
import com.resto.transaction.service.TransactionService;
import com.resto.transaction.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("v1/create")
    ResponseEntity<Response> saveTransaction(@RequestBody TransactionRequest request) {
        TransactionDto transactionDto = transactionService.saveTransaction(request);
        Response response;

        if (!ObjectUtils.isEmpty(transactionDto)){
            response = new Response(transactionDto, "Success insert", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new Response(null, "Failed to insert", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("v1/find-all")
    ResponseEntity<Response> getTransactions() {
        List<TransactionDto> listData = transactionService.findAll();
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
    ResponseEntity<Response> getTransactionById(@PathVariable Long id) {
        TransactionDto data = transactionService.getTransactionById(id);
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
