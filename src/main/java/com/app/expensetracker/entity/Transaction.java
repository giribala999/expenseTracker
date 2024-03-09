package com.app.expensetracker.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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
    @Column(name="trans_id",nullable = false)
    String trans_id;

    @Column(name="Transaction name")
    String transaction_name;

    @Column(name="Lender")
    String lender;

    @Column(name="Borrower")
    String borrower;

    @Column(name="Value")
    double value;

    @Column(name="Creation Time")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="Last Updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name="category_name")
    String category_name;

//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private  User user;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> trans_users;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private  Category category;
}