package com.app.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="Id",nullable = false)
    private String id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

//    @Column(name="balance")
//    private double balance;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Category> categories;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Transaction> transactions;

    @ManyToMany(mappedBy = "trans_users")
    private List<Transaction> user_transactions;

}