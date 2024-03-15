package com.app.expensetracker.controller;
import com.app.expensetracker.dto.UserCreateRequest;
import com.app.expensetracker.entity.User;
import com.app.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user_list")
    public String getAllUsers(Model model) {
       model.addAttribute("user_list", userService.getAllUsers());
        return "user";

    }

    @GetMapping("/create_form")
    public String createUserForm(Model model) {

        // create student object to hold student form data
        User user = new User();
        model.addAttribute("user", user);
        return "user_create";

    }
    @PostMapping("/create")
    public  String createUser( @Valid UserCreateRequest userCreateRequest,Model model) throws Exception {
        model.addAttribute("user",userService.createUser(userCreateRequest));
        model.addAttribute("message", "You have registered successfully.");
        return "redirect:/user/user_list";
    }
    @PutMapping("/update/{user_id}")
    public  String updateCategory(@PathVariable("user_id") String user_id, @RequestBody @Valid UserCreateRequest userCreateRequest) throws Exception {
       userService.createUser(userCreateRequest);
        return "user_create";
    }
    @GetMapping("/get/{user_id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String user_id) {
        return userService.getUserById(user_id);
    }

    @PostMapping("/delete/{user_id}")
    public String deleteUserById(@PathVariable String user_id) {
        userService.deleteUserById(user_id);
        return "redirect:/user";
    }

}