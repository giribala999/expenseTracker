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
    private String item;

    private String lend_id;

    private String borrow_id;

    @NonNull
    private double val;

    @NotBlank
    private String cat_id;

    public Transaction to(){
        return Transaction.builder()
                .trans_id(UUID.randomUUID().toString())
                .item(this.item)
                .lend_id(this.lend_id)
                .borrow_id(this.borrow_id)
                .val(this.val)
                .cat_id(this.cat_id)
                .build();
    }
}