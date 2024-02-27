package com.app.expensetracker.controller;

import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/create")
    public  Transaction createTransaction(@RequestBody @Valid TransactionCreateRequest transactionCreateRequest){
        return transactionService.createTransaction(transactionCreateRequest);
    }
    @PutMapping("/update/{trans_id}")
    public Transaction updateTransaction(@PathVariable("trans_id") String trans_id, @RequestBody @Valid TransactionCreateRequest transactionCreateRequest){
        return transactionService.updateTransaction(trans_id, transactionCreateRequest);
    }
    @GetMapping("/get/{trans_id}")
    public ResponseEntity<Optional<Transaction>> getTransactionById(@PathVariable String trans_id) {
        return transactionService.getTransactionById(trans_id);
    }
    @PostMapping("/delete/{trans_id}")
    public ResponseEntity<Optional<Transaction>> deleteTransactionById(@PathVariable String trans_id) {
        return transactionService.deleteTransactionById(trans_id);
    }


}