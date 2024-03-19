package com.app.expensetracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
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
    @Column(name="user_id",nullable = false)
    private String user_id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @JsonBackReference
    @ManyToMany(mappedBy = "cat_users",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Category> categories;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Transaction> transactions;

    @JsonBackReference
    @ManyToMany(mappedBy = "trans_users",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> user_transactions=new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserResponse> userResponse=new ArrayList<>();

}