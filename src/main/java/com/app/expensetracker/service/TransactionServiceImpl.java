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
    public Transaction createTransaction(TransactionCreateRequest transactionCreateRequest) throws Exception {
        Transaction transaction = transactionCreateRequest.to();
        Transaction local= this.transactionRepository.findByTransactionName(transaction.getTransactionName());

        if(local!= null) {
            System.out.println("Transaction already present!!!");
            throw new Exception("Transaction already present!!!");
        }

        else{
            List<Double> l_trans_list=new ArrayList<>();
            List<Double> b_trans_list=new ArrayList<>();

            double l,b;

            l= -(transaction.getPrice());
            l_trans_list.add(l);

            b=transaction.getPrice();
            b_trans_list.add(b);

            User lender=this.userRepository.findByFirstNameAndLastName(transaction.getLenderFirstName(), transaction.getLenderLastName());
            User borrower=this.userRepository.findByFirstNameAndLastName(transaction.getBorrowerFirstName(), transaction.getBorrowerLastName());

            Category category=this.categoryRepository.findByCategoryName(transaction.getCategoryName());
//category.getCat_users()
            List<User> cat_users;
            if(category.getCat_users()==null){
                cat_users=new ArrayList<>();
                cat_users.add(lender);
                cat_users.add(borrower);

            }
            else {
                cat_users = category.getCat_users();
               // System.out.println("CATEGORY----------------USERS------------------"+cat_users);
                if(cat_users.contains(lender)){}
                else {
                    cat_users.add(lender);
                }

                if(cat_users.contains(borrower)){}
                else {
                    cat_users.add(borrower); //stores users in category
                }
            }

            List<User> trans_users = new ArrayList<>();
            trans_users.add(lender);
            trans_users.add(borrower); //store users in transaction

            category.setCat_users(cat_users);
            transaction.setTrans_users(trans_users);

            transaction.setCategory(category); //to store transaction in category


            UserResponse local1=this.userResponseRepository.findByUserAndCategoryName(lender,transaction.getCategoryName());
            UserResponse local2=this.userResponseRepository.findByUserAndCategoryName(borrower, transaction.getCategoryName());


            UserResponse l_userResponse = new UserResponse();
            if(local1==null) {
                l_userResponse.setBalance(l);
            }
            else{
                l=l+local1.getBalance();
                l_userResponse.setBalance(l);
            }
            l_userResponse.setUser(lender);
            l_userResponse.setCategoryName(category.getCategoryName());
            l_userResponse.setBalance(l);
            l_userResponse.setTrans_list(l_trans_list);
            userResponseRepository.save(l_userResponse);


            UserResponse b_userResponse = new UserResponse();
            if(local2==null) {
                b_userResponse.setBalance(b);
            }
            else{
                b=b+ local2.getBalance();
                b_userResponse.setBalance(b);
            }
            b_userResponse.setUser(borrower);
            b_userResponse.setCategoryName(category.getCategoryName());
            b_userResponse.setTrans_list(b_trans_list);
            userResponseRepository.save(b_userResponse);

            return transactionRepository.save(transaction);
        }

    }

    @Override
    @Transactional
    public Transaction getTransactionById(String transaction_id){
        return  transactionRepository.findById(transaction_id).get();
    }
    @Override
    @Transactional
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Transaction transaction){
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