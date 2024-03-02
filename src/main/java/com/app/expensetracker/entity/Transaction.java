package com.app.expensetracker.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    // @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id")
    // private  User user;

    // @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    // @JoinColumn(name = "cat_id")
    // private  Category category;
}
