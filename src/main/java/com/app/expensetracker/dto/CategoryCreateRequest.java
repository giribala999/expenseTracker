package com.app.expensetracker.dto;
import com.app.expensetracker.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

/**
 * CategoryCreateRequest is a Data Transfer Object (DTO) for creating a new Category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryCreateRequest {
    @NotBlank // The name of the category cannot be blank.
    private String categoryName;

    public Category to(){
        return Category.builder()
                .cat_id(UUID.randomUUID().toString())
                .categoryName(this.categoryName)
                .build();
    }
}