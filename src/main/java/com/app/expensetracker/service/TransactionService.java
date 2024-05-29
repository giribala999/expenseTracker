package com.app.expensetracker.service;
import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * TransactionService provides methods for managing transactions in the expense tracker system.
 */
public interface TransactionService {

    Transaction createTransaction(TransactionCreateRequest transactionCreateRequest) throws Exception;
    Transaction getTransactionById(String transaction_id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Transaction transaction) throws Exception;
    ResponseEntity<Optional<Transaction>> deleteTransactionById(String transaction_id);
}