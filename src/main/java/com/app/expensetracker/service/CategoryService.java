package com.app.expensetracker.service;

import com.app.expensetracker.dto.CategoryCreateRequest;
import com.app.expensetracker.entity.Category;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(CategoryCreateRequest categoryCreateRequest);
    ResponseEntity<Optional<Category>> getCategoryById(String category_id);
    Category updateCategory(String categoryId, CategoryCreateRequest categoryCreateRequest);
    ResponseEntity<Optional<Category>> deleteCategoryById(String category_id);
}