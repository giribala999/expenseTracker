package com.app.expensetracker.service;

import com.app.expensetracker.dto.CategoryCreateRequest;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(CategoryCreateRequest categoryCreateRequest);
    ResponseEntity<Optional<Category>> getCategoryById(String category_id);
    List<Category> getAllCategories();
    Category updateCategory(String categoryId, CategoryCreateRequest categoryCreateRequest);
    ResponseEntity<Optional<Category>> deleteCategoryById(String category_id);
}