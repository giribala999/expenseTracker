package com.app.expensetracker.service;

import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction createTransaction(TransactionCreateRequest transactionCreateRequest) throws Exception;
    Transaction getTransactionById(String transaction_id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Transaction transaction);
    ResponseEntity<Optional<Transaction>> deleteTransactionById(String transaction_id);
}