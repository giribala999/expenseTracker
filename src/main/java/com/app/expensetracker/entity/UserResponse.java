package com.app.expensetracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "user response")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserResponse {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="response_id",nullable = false)
    private String response_id;

    @Column(name="categoryName")
    private String categoryName;

    @Column(name="transaction list")
    private List<Double> trans_list;

    @Column(name="balance")
    private double balance;

    @JsonManagedReference
    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}