package com.app.expensetracker.controller;

import com.app.expensetracker.entity.Transaction;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/controller")
public class TransactionController {
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    String trans_id,lend_id,borrow_id,user_id,cat_id,item;
    double price, val;
    HashMap<String, Transaction> map = new HashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Double> trans = new ArrayList<>();

    static HashMap<String, CatTransResponse> user_trans_map = new HashMap<>();


    @GetMapping("/transaction")
    public List<Transaction> getTransaction() {
        System.out.println("GETMAPPING 1  "+transactions);
        return transactions;
    }

    @GetMapping("/transaction/{trans_id}")
    public Transaction getTransaction(@PathVariable String trans_id) {
        System.out.println("GETMAPPING   trans_id:  "+trans_id+"---- transaction  :"+map.get(trans_id));
        if (map.containsKey(trans_id)) {
            return map.get(trans_id);
        }
        else {
            throw new UserNotFoundException("trans_id : '" +trans_id+"' not found");
        }

    }

    @PostMapping("/transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction){

        UUID uuid= UUID.randomUUID();
        trans_id=uuid.toString();
        item=transaction.getItem();
        lend_id=transaction.getLend_id();
        borrow_id=transaction.getBorrow_id();
        if(lend_id==null){
            val=-(transaction.getVal());
            user_id=borrow_id;
        }
        else{
            val=transaction.getVal();
            user_id=lend_id;
        }
        cat_id=transaction.getCat_id();
        System.out.println("The value of item "+item+" = "+val);
        dateCreated=LocalDateTime.now();
        transaction=new Transaction(trans_id,item,lend_id,borrow_id,val,cat_id,dateCreated,lastUpdated);
        transactions.add(transaction);
        map.put(trans_id,transaction);

        trans.add(val);
        CatTransResponse cat_trans =new CatTransResponse(cat_id,trans);
        System.out.println("After making the cat_trans object  : "+cat_trans);
`        user_trans_map.put(user_id,cat_trans);
        System.out.println("Complete making the cat_trans object");

        return transaction;

    }

    @PutMapping("/transaction/{trans_id}")
    public Transaction updateTransaction(@PathVariable String trans_id , @RequestBody Transaction transaction) {

        transactions.remove(getTransaction(trans_id));
        item=transaction.getItem();
        lend_id=transaction.getLend_id();
        borrow_id=transaction.getBorrow_id();
        if(lend_id==null){
            val=-(transaction.getVal());
            user_id=borrow_id;
        }
        else{
            val=transaction.getVal();
            user_id=lend_id;
        }
        cat_id=transaction.getCat_id();
        lastUpdated=LocalDateTime.now();

        transaction=new Transaction(trans_id,item,lend_id,borrow_id,val,cat_id,dateCreated,lastUpdated);
        transactions.add(transaction);

        map.put(trans_id,transaction);
        trans.add(val);

        CatTransResponse cat_trans =new CatTransResponse(cat_id,trans);

        System.out.println("After making the cat_trans object  : "+cat_trans);

        user_trans_map.put(user_id,cat_trans);
        System.out.println("Complete making the cat_trans object");
        return transaction;
    }

    @DeleteMapping("/transaction/{trans_id}")
    public String deleteTransaction(@PathVariable String trans_id ) {
        transactions.remove(getTransaction(trans_id));
        map.remove(trans_id);
        return "Transaction deleted";
    }


}
