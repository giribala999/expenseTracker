package com.app.expensetracker.dao;

import com.app.expensetracker.entity.User;
import com.app.expensetracker.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, String> {


}
