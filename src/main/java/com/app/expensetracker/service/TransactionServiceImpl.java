package com.app.expensetracker.service;

import com.app.expensetracker.dao.CategoryRepository;
import com.app.expensetracker.dao.TransactionRepository;
import com.app.expensetracker.dao.UserRepository;
import com.app.expensetracker.dao.UserResponseRepository;
import com.app.expensetracker.dto.TransactionCreateRequest;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.entity.User;
import com.app.expensetracker.entity.UserResponse;
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
    @Autowired
    private UserResponseRepository userResponseRepository;


    @Override
    @Transactional
    public Transaction createTransaction(TransactionCreateRequest transactionCreateRequest){
        Transaction transaction = transactionCreateRequest.to();

        //List<User> cat_users=new ArrayList<>();

        //List<User> trans_users=new ArrayList<>();

        List<Double> b_trans_list=new ArrayList<>();
        List<Double> l_trans_list=new ArrayList<>();
        double b,l;

        b=transaction.getValue();
        l= -(transaction.getValue());
        b_trans_list.add(b);
        l_trans_list.add(l);

        User borrower=this.userRepository.findByFirstNameAndLastName(transaction.getBorrowerFirstName(), transaction.getBorrowerLastName());
        User lender=this.userRepository.findByFirstNameAndLastName(transaction.getLenderFirstName(), transaction.getLenderLastName());
        Category category=this.categoryRepository.findByCategoryName(transaction.getCategoryName());

        List<User> cat_users;
                if(category.getCat_users()==null){
                       cat_users=new ArrayList<>();
                        cat_users.add(borrower);
                        cat_users.add(lender);
                        }
                else {
                        cat_users = category.getCat_users();
                         if(cat_users.contains(borrower)){}
                         else {
                             cat_users.add(borrower); //stores users in category
                             }

                         if(cat_users.contains(lender)){}
                         else {
                             cat_users.add(lender);
                             }
                     }

        List<User> trans_users = new ArrayList<>();
        trans_users.add(borrower); //store users in transaction
        trans_users.add(lender);


        category.setCat_users(cat_users);
        transaction.setTrans_users(trans_users);

        transaction.setCategory(category); //to store transaction in category


        UserResponse b_userResponse = new UserResponse();
        b_userResponse.setUser(borrower);
        b_userResponse.setCategoryName(category.getCategoryName());
        b_userResponse.setBalance(b);
        b_userResponse.setTrans_list(b_trans_list);
        userResponseRepository.save(b_userResponse);

        UserResponse l_userResponse = new UserResponse();
        l_userResponse.setUser(lender);
        l_userResponse.setCategoryName(category.getCategoryName());
        l_userResponse.setBalance(l);
        l_userResponse.setTrans_list(l_trans_list);
        userResponseRepository.save(l_userResponse);

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