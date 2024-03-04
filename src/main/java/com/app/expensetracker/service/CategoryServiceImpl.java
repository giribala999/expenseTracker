package com.app.expensetracker.service;
import com.app.expensetracker.dao.CategoryRepository;
import com.app.expensetracker.dto.CategoryCreateRequest;
import com.app.expensetracker.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category createCategory(CategoryCreateRequest categoryCreateRequest){
        Category category = categoryCreateRequest.to();
        return categoryRepository.save(category);
    }


    @Override
    @Transactional
    public ResponseEntity<Optional<Category>> getCategoryById(String category_id){

        Optional<Category> category =  categoryRepository.findById(category_id);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(category);
        }

    }

    @Override
    @Transactional
    public Category updateCategory(String categoryId, CategoryCreateRequest categoryCreateRequest){
        Category category = categoryCreateRequest.to();
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Category>> deleteCategoryById(String category_id){
        Optional<Category> category =  categoryRepository.findById(category_id);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        else{
            categoryRepository.deleteById(category_id);
            return ResponseEntity.ok(category);
        }
    }
}