package com.app.expensetracker.service;

import com.app.expensetracker.dto.UserCreateRequest;
import com.app.expensetracker.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * UserService is an interface providing methods for managing users in the expense tracker system.
 */
public interface UserService {

    User createUser(UserCreateRequest userCreateRequest) throws Exception;
    User getUserById(String user_id);

    List<User> getAllUsers();
    User updateUser(User user) throws Exception;
    ResponseEntity<Optional<User>> deleteUserById(String user_id);


    // New method for user authentication
    User authenticateUser(String firstName,String lastName, String password);
}