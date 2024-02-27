package com.app.expensetracker.service;

import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Transaction;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public interface TransactionService {

    Transaction createTransaction(TransactionCreateRequest transactionCreateRequest);
    ResponseEntity<Optional<Transaction>> getTransactionById(String transaction_id);

    Transaction updateTransaction(String transId, TransactionCreateRequest transactionCreateRequest);
    ResponseEntity<Optional<Transaction>> deleteTransactionById(String transaction_id);
}