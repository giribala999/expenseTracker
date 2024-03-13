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
    private String transaction_name;

    private String lender;

    private String borrower;

    @NonNull
    private double value;

    @NotBlank
    private String category_name;

    public Transaction to(){
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .transaction_name(this.transaction_name)
                .lender(this.lender)
                .borrower(this.borrower)
                .value(this.value)
                .category_name(this.category_name)
                .build();
    }
}