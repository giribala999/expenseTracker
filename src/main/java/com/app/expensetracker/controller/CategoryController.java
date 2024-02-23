package com.app.expensetracker.controller;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public  void createCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
    }
    @PostMapping("/update")
    public  void updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable String category_id) {
        return categoryService.getCategoryById(category_id);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Optional<Category>> deleteCategoryById(@PathVariable String category_id) {
        return categoryService.deleteCategoryById(category_id);
    }

}
