package com.app.expensetracker.dto;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.entity.User;
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

//    @Size(min=2,message="Enter 2 users only")
//    private List<User> users;

    public Category to(){
        return Category.builder()
                .id(UUID.randomUUID().toString())
                .categoryName(this.categoryName)
                .build();
    }
}