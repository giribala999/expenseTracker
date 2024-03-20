package com.app.expensetracker.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="Id",nullable = false)
    String id;

    @Column(name="Transaction name")
    String transactionName;

    @Column(name="lenderFirstName")
    String lenderFirstName;

    @Column(name="lenderLastName")
    String lenderLastName;

    @Column(name="borrowerFirstName")
    String borrowerFirstName;

    @Column(name="borrowerLastName")
    String borrowerLastName;

    @Column(name="Price")
    double price;

    @Column(name="Creation Time")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="Last Updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name="categoryName")
    String categoryName;

//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private  User user;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> trans_users=new ArrayList<>();

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private  Category category;
}