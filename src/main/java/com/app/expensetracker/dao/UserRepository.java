package com.app.expensetracker.dao;

import com.app.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByFirstNameAndLastName(String firstName,String lastName); //Finds a user by their first and last name.
    User findByFirstNameAndLastNameAndPassword(String firstName,String lastName, String password); //Finds a user by their first name, last name, and password.
}