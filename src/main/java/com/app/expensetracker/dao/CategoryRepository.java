package com.app.expensetracker.dao;

import com.app.expensetracker.entity.Category;
import com.app.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
