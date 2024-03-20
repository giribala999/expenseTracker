package com.app.expensetracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class Category {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="Id",nullable = false)
    private String id;

    @Column(name="categoryName")
    private String categoryName;

    @JsonManagedReference // to prevent infinite json recursion
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> cat_users=new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> transactions=new ArrayList<>();
}
