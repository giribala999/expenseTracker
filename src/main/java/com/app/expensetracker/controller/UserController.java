package com.app.expensetracker.controller;
import com.app.expensetracker.entity.User;
import com.app.expensetracker.service.UserService;
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
        public  void createUser(@RequestBody User user){
            userService.saveUser(user);
        }
        @PostMapping("/update")
        public  void updateUser(@RequestBody User user){
            userService.updateUser(user);
        }
        @GetMapping("/get/{id}")
        public ResponseEntity<Optional<User>> getUserById(@PathVariable String user_id) {
            return userService.getUserById(user_id);
        }
        @PostMapping("/delete/{id}")
        public ResponseEntity<Optional<User>> deleteUserById(@PathVariable String user_id) {
            return userService.deleteUserById(user_id);
        }

}

