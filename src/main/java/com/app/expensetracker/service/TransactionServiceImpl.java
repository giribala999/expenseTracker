package com.app.expensetracker.service;

import com.app.expensetracker.dao.TransactionRepository;
import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

//    public void saveTransaction(Transaction transaction){
//
//        transactionRepository.save(transaction);
//    }

    @Override
    @Transactional
    public Transaction createTransaction(TransactionCreateRequest transactionCreateRequest){
        Transaction transaction = transactionCreateRequest.to();
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Transaction>> getTransactionById(String transaction_id){

        Optional<Transaction> transaction =  transactionRepository.findById(transaction_id);
        if(transaction == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(transaction);
        }

    }
//    public void updateTransaction(Transaction transaction){
//        transactionRepository.save(transaction);
//    }

    @Override
    @Transactional
    public Transaction updateTransaction(String transId, TransactionCreateRequest transactionCreateRequest){
        Transaction transaction = transactionCreateRequest.to();
        return transactionRepository.save(transaction);
    }


    @Override
    @Transactional
    public ResponseEntity<Optional<Transaction>> deleteTransactionById(String transaction_id){
        Optional<Transaction> transaction =  transactionRepository.findById(transaction_id);
        if(transaction == null){
            return ResponseEntity.notFound().build();
        }
        else{
            transactionRepository.deleteById(transaction_id);
            return ResponseEntity.ok(transaction);
        }
    }
}