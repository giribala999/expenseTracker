package com.app.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @Column(name="cat_id",nullable = false)
    private String cat_id;

    @Column(name="categoryName")
    private String categoryName;

    @Column(name="users")
    private List<String> users;

    // @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id")
    // private  User user;
}
