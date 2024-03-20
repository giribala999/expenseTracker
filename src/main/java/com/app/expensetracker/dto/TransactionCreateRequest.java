package com.app.expensetracker.dto;
import com.app.expensetracker.entity.Transaction;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionCreateRequest {
    @NotBlank
    private String transactionName;

    private String lenderFirstName;
    private String lenderLastName;
    private String borrowerFirstName;
    private String borrowerLastName;

    @NonNull
    private double price;

    @NotBlank
    private String categoryName;

    public Transaction to(){
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
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