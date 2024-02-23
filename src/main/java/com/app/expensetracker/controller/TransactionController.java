package com.app.expensetracker.controller;

import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.service.CatTransResponse;
import com.app.expensetracker.service.TransactionService;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/create")
    public  void createTransaction(@RequestBody Transaction transaction){
        transactionService.saveTransaction(transaction);
    }
    @PostMapping("/update")
    public  void updateTransaction(@RequestBody Transaction transaction){
        transactionService.updateTransaction(transaction);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Transaction>> getTransactionById(@PathVariable String transaction_id) {
        return transactionService.getTransactionById(transaction_id);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Optional<Transaction>> deleteTransactionById(@PathVariable String transaction_id) {
        return transactionService.deleteTransactionById(transaction_id);
    }


}
