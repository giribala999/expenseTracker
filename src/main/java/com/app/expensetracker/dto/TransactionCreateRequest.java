package com.app.expensetracker.dto;
import com.app.expensetracker.entity.Transaction;
import com.app.expensetracker.entity.User;
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

//    private User lender;
//
//    private User borrower;

    private String lenderFirstName;
    private String lenderLastName;
    private String borrowerFirstName;
    private String borrowerLastName;

    @NonNull
    private double value;

    @NotBlank
    private String categoryName;

    public Transaction to(){
        return Transaction.builder()
                .trans_id(UUID.randomUUID().toString())
                .transaction_name(this.transaction_name)
                .lenderFirstName(this.lenderFirstName)
                .lenderLastName(this.lenderLastName)
                .borrowerFirstName(this.borrowerFirstName)
                .borrowerLastName(this.borrowerLastName)
                .value(this.value)
                .categoryName(this.categoryName)
                .build();
    }
}