package com.app.expensetracker.controller;
import com.app.expensetracker.dto.CategoryCreateRequest;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.entity.User;
import com.app.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public  Category createCategory(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest){
        return categoryService.createCategory(categoryCreateRequest);
    }
    @PutMapping("/update/{catId}")
    public Category updateCategory(@PathVariable("catId") String catId, @RequestBody @Valid CategoryCreateRequest categoryCreateRequest){
        return categoryService.updateCategory(catId, categoryCreateRequest);
    }
    @GetMapping("/get/{catId}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable String catId) {
        return categoryService.getCategoryById(catId);
    }

    @GetMapping("/get")
    public List<Category> getAllCategories() {
         return categoryService.getAllCategories();
    }
    @PostMapping("/delete/{catId}")
    public ResponseEntity<Optional<Category>> deleteCategoryById(@PathVariable String catId) {
        return categoryService.deleteCategoryById(catId);
    }

}