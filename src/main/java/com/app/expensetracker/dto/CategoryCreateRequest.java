package com.app.expensetracker.dto;
import com.app.expensetracker.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryCreateRequest {
    @NotBlank
    private String categoryName;

    @Size(min=2,message="Enter 2 users")
    private List<String> users;

    public Category to(){
        return Category.builder()
                .cat_id(UUID.randomUUID().toString())
                .categoryName(this.categoryName)
                .users(this.users)
                .build();
    }
}