package com.app.expensetracker.service;

import com.app.expensetracker.dao.CategoryRepository;
import com.app.expensetracker.dao.TransactionRepository;
import com.app.expensetracker.dao.UserRepository;
import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public Transaction createTransaction(TransactionCreateRequest transactionCreateRequest){
        Transaction transaction = transactionCreateRequest.to();

        List<User> cat_users=new ArrayList<>();

        User borrower=this.userRepository.findByFirstNameAndLastName(transaction.getBorrowerFirstName(), transaction.getBorrowerLastName());
        User lender=this.userRepository.findByFirstNameAndLastName(transaction.getLenderFirstName(), transaction.getLenderLastName());

        cat_users.add(borrower);
        cat_users.add(lender);

        Category category=this.categoryRepository.findByCategoryName(transaction.getCategoryName());
        category.setCat_users(cat_users);

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
    @Override
    @Transactional
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

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