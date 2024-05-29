package com.app.expensetracker.service;
import com.app.expensetracker.dao.CategoryRepository;
import com.app.expensetracker.dto.CategoryCreateRequest;
import com.app.expensetracker.entity.Category;
import com.app.expensetracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * CategoryServiceImpl is an implementation of the CategoryService interface.
 * It provides methods for managing categories in the expense tracker system.
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category createCategory(CategoryCreateRequest categoryCreateRequest) throws Exception {
        Category category = categoryCreateRequest.to();
        Category local= this.categoryRepository.findByCategoryName(category.getCategoryName());

        if(local!= null) {
            System.out.println("Category already present!!!");
            throw new Exception("Category already present!!!");
        }
        else{
            return categoryRepository.save(category);
        }
    }


    @Override
    @Transactional
    public Category getCategoryById(String category_id){
        return categoryRepository.findById(category_id).get();
    }

    @Override
    @Transactional
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category updateCategory(Category category)throws Exception{

        Category local= this.categoryRepository.findByCategoryName(category.getCategoryName());

        if(local!= null) {
            System.out.println("Category already present!!!");
            throw new Exception("Category already present!!!");
        }
        else{
            return categoryRepository.save(category);
        }
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