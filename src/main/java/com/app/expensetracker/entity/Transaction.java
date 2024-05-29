package com.app.expensetracker.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    String trans_id;

    @NotBlank(message = "Do not leave blank")
    @Column(name="Transaction name")
    String transactionName;

    @NotBlank(message = "Do not leave blank")
    @Column(name="lenderFirstName")
    String lenderFirstName;

    @NotBlank(message = "Do not leave blank")
    @Column(name="lenderLastName")
    String lenderLastName;

    @NotBlank(message = "Do not leave blank")
    @Column(name="borrowerFirstName")
    String borrowerFirstName;

    @NotBlank(message = "Do not leave blank")
    @Column(name="borrowerLastName")
    String borrowerLastName;

    @Min(value=1)
    @Column(name="Price")
    double price;

    @Column(name="Creation Time")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="Last Updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @NotBlank(message = "Do not leave blank")
    @Column(name="categoryName")
    String categoryName;

    /**
     * The list of users associated with this transaction.
     */
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> trans_users=new ArrayList<>();


    /**
     * The category associated with this transaction.
     */
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private  Category category;
}