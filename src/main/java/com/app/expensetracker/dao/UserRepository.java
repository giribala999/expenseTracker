package com.app.expensetracker.dao;

import com.app.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    User findByFirstNameAndLastName(String firstName,String lastName);
}