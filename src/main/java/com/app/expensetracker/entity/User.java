package com.app.expensetracker.entity;

import com.app.expensetracker.controller.CatTransResponse;
import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id",nullable = false)
    private String user_id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    CatTransResponse catTran;
    @Column(name="balance")
    private double balance;
    public User(){

    }

    public User(String user_id, String firstName, String lastName, CatTransResponse catTran, double balance) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.catTran = catTran;
        this.balance = balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CatTransResponse getCatTran() {
        return catTran;
    }

    public void setCatTran(CatTransResponse catTran) {
        this.catTran = catTran;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", catTran=" + catTran +
                ", balance=" + balance +
                '}';
    }
}
