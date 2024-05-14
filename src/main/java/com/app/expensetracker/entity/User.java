package com.app.expensetracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name="Id",nullable = false)
    private String id;

    @NotBlank(message = "Do not leave blank")
    @Column(name="firstName")
    private String firstName;

    @NotBlank(message = "Do not leave blank")
    @Column(name="lastName")
    private String lastName;

    private String password;

    @JsonBackReference
    @ManyToMany(mappedBy = "cat_users",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Category> categories;

    @JsonBackReference
    @ManyToMany(mappedBy = "trans_users",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> user_transactions=new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserResponse> userResponse=new ArrayList<>();

}