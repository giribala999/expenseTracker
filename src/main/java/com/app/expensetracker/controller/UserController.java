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

    @GetMapping("/login_form")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserCreateRequest userCreateRequest, Model model) {
        User user = userService.authenticateUser(userCreateRequest.getFirstName(),userCreateRequest.getLastName(), userCreateRequest.getPassword());
        if (user != null) {
            System.out.println("AUTHENTICATION SUCCESSFUL**************************************************************************************");
            // Authentication successful, redirect to home page or any other desired page
            return "redirect:/user/user_list";
        }
        else {
            System.out.println("AUTHENTICATION FAILED*******************************************************************************************");

            return "redirect:/user/login_form"; //login page template named "login"
        }
    }

    @GetMapping("/user_list")
    public String getAllUsers(Model model) {
       model.addAttribute("user_list", userService.getAllUsers());
        return "user";
    }

    @GetMapping("/create_form")
    public String createUserForm(Model model) {

        // create user object to hold user form data
        User user = new User();
        model.addAttribute("user", user);
        return "user_create";
    }


    @PostMapping("/create")
    public  String createUser( @Valid UserCreateRequest userCreateRequest,Model model) throws Exception {
        model.addAttribute("user",userService.createUser(userCreateRequest));

        return "success";
    }

    @GetMapping("/update_form/{user_id}")
    public String editUserForm(@PathVariable String user_id, Model model) {
        model.addAttribute("user", userService.getUserById(user_id));
        return "user_edit";
    }
    @PostMapping("/update/{user_id}")
    public  String updateCategory(@PathVariable("user_id") String user_id, @ModelAttribute("user")User user,Model model) throws Exception {
        // get user from database by id
        User existingUser = userService.getUserById(user_id);
        existingUser.setId(user_id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPassword(user.getPassword());

        // save updated user object
        userService.updateUser(existingUser);

        return "update";
    }
    @GetMapping("/get/{user_id}")
    public String getUserById(@PathVariable String user_id, Model model) {
        model.addAttribute("user", userService.getUserById(user_id));
        return "user_details";
    }

    @GetMapping("/delete/{user_id}")
    public String deleteUserById(@PathVariable String user_id) {
        userService.deleteUserById(user_id);
        return "delete";
    }

}