package com.app.expensetracker.controller;
import com.app.expensetracker.dto.UserCreateRequest;
import com.app.expensetracker.entity.User;
import com.app.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public  User createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        return userService.createUser(userCreateRequest);
    }
    @PutMapping("/update/{user_id}")
    public  User updateCategory(@PathVariable("user_id") String user_id, @RequestBody @Valid UserCreateRequest userCreateRequest){
        return userService.updateUser(user_id, userCreateRequest);
    }
    @GetMapping("/get/{user_id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String user_id) {
        return userService.getUserById(user_id);
    }
    @PostMapping("/delete/{user_id}")
    public ResponseEntity<Optional<User>> deleteUserById(@PathVariable String user_id) {
        return userService.deleteUserById(user_id);
    }

}