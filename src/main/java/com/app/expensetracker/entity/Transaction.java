package com.app.expensetracker.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="trans_id",nullable = false)
    String trans_id;

    @Column(name="item")
    String item;

    @Column(name="lend_id")
    String lend_id;

    @Column(name="borrow_id")
    String borrow_id;

    @Column(name="val")
    double val;

    @Column(name="cat_id")
    String cat_id;

    //List<Integer> trans;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public Transaction() {
    }

//    public Transaction(String trans_id, String item, String lend_id,String borrow_id,double val, String cat_id) {
//        this.trans_id = trans_id;
//        this.item = item;
//        this.lend_id=lend_id;
//        this.borrow_id=borrow_id;
//        this.val = val;
//        this.cat_id = cat_id;
//    }

    public Transaction(String trans_id, String item, String lend_id, String borrow_id, double val, String cat_id, LocalDateTime dateCreated, LocalDateTime lastUpdated) {
        this.trans_id = trans_id;
        this.item = item;
        this.lend_id = lend_id;
        this.borrow_id = borrow_id;
        this.val = val;
        this.cat_id = cat_id;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLend_id() {
        return lend_id;
    }

    public void setLend_id(String lend_id) {
        this.lend_id = lend_id;
    }

    public String getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(String borrow_id) {
        this.borrow_id = borrow_id;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trans_id='" + trans_id + '\'' +
                ", item='" + item + '\'' +
                ", lend_id='" + lend_id + '\'' +
                ", borrow_id='" + borrow_id + '\'' +
                ", val=" + val +
                ", cat_id='" + cat_id + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
