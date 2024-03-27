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
            User lender=this.userRepository.findByFirstNameAndLastName(transaction.getLenderFirstName(), transaction.getLenderLastName());
            User borrower=this.userRepository.findByFirstNameAndLastName(transaction.getBorrowerFirstName(), transaction.getBorrowerLastName());

            Category category=this.categoryRepository.findByCategoryName(transaction.getCategoryName());

            List<User> cat_users;
                if(category.getCat_users()==null){
                    cat_users=new ArrayList<>();
                    cat_users.add(lender);
                    cat_users.add(borrower);
                }
                else {
                    cat_users = category.getCat_users();

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

            double l,b, bal1=0,bal2=0;
            l= -(transaction.getPrice());
            b=transaction.getPrice();

            UserResponse l_userResponse = new UserResponse();
            List<Double> l_trans_list;
            if(local1==null) {
                bal1=l;
                l_trans_list=new ArrayList<>();
            }
            else{
                bal1=l+local1.getBalance();
                l_trans_list=local1.getTrans_list();
                userResponseRepository.deleteById(local1.getResponse_id());//not working
            }
            l_userResponse.setUser(lender);
            l_userResponse.setCategoryName(category.getCategoryName());
            l_userResponse.setBalance(bal1);
            l_trans_list.add(l);
            l_userResponse.setTrans_list(l_trans_list);

//            if(local1!=null) {
//                System.out.println("ACTIVITY ID : "+ local1.getResponse_id());
//                userResponseRepository.deleteById(local1.getResponse_id());
//                System.out.println("SUCCESSFUL LOCAL 1 DELETED");
//            }
            userResponseRepository.save(l_userResponse);



            UserResponse b_userResponse = new UserResponse();
            List<Double> b_trans_list;
            if(local2==null) {
                bal2=b;
                b_trans_list=new ArrayList<>();
            }
            else{
                bal2=b+ local2.getBalance();
                b_trans_list=local2.getTrans_list();
                userResponseRepository.deleteById(local2.getResponse_id());
            }
            b_userResponse.setUser(borrower);
            b_userResponse.setCategoryName(category.getCategoryName());
            b_userResponse.setBalance(bal2);
            b_trans_list.add(b);
            b_userResponse.setTrans_list(b_trans_list);

//            if(local2!=null) {
//                System.out.println("ACTIVITY ID : "+ local2.getResponse_id());
//                userResponseRepository.deleteById(local2.getResponse_id());
//                System.out.println("SUCCESSFUL LOCAL 2 DELETED");
//            }
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