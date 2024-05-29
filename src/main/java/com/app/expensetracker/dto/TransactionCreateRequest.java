package com.app.expensetracker.dto;
import com.app.expensetracker.entity.Transaction;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

/**
 * TransactionCreateRequest is a Data Transfer Object (DTO) for creating a new Transaction.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionCreateRequest {
    @NotBlank //The name of the transaction cannot be blank.
    private String transactionName;

    private String lenderFirstName;
    private String lenderLastName;
    private String borrowerFirstName;
    private String borrowerLastName;

    @NonNull //price associated with the transaction cannot be null.
    private double price;

    @NotBlank // The name of the category cannot be blank.
    private String categoryName;

    public Transaction to(){
        return Transaction.builder()
                .trans_id(UUID.randomUUID().toString())
                .transactionName(this.transactionName)
                .lenderFirstName(this.lenderFirstName)
                .lenderLastName(this.lenderLastName)
                .borrowerFirstName(this.borrowerFirstName)
                .borrowerLastName(this.borrowerLastName)
                .price(this.price)
                .categoryName(this.categoryName)
                .build();
    }
}