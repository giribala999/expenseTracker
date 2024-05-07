package com.app.expensetracker.service;

import com.app.expensetracker.dto.UserCreateRequest;
import com.app.expensetracker.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.
        List;
import java.util.Optional;

public interface UserService {

    User createUser(UserCreateRequest userCreateRequest) throws Exception;
    User getUserById(String user_id);

    List<User> getAllUsers();
    User updateUser(User user);
    ResponseEntity<Optional<User>> deleteUserById(String user_id);

}