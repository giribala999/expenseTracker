package com.app.expensetracker.controller;
import com.app.expensetracker.dto.UserCreateRequest;
import com.app.expensetracker.entity.User;
import com.app.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * UserController handles HTTP requests for user-related operations.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login_form")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login"; // Displays the login form.
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserCreateRequest userCreateRequest, Model model) //Authenticates the user based on login credentials.
    {
        User user = userService.authenticateUser(userCreateRequest.getFirstName(),userCreateRequest.getLastName(), userCreateRequest.getPassword());
        if (user != null) {
            // Authentication successful, and redirects to the desired page
            return "redirect:/user/user_list";
        }
        else {

            return "redirect:/user/login_form"; //login page template named "login"
        }
    }

    @GetMapping("/user_list")
    public String getAllUsers(Model model) //Retrieves and displays the list of all users.
    {
       model.addAttribute("user_list", userService.getAllUsers());
        return "user";
    }

    @GetMapping("/create_form")
    public String createUserForm(Model model) //Displays the user creation form.
    {
        // create user object to hold user form data
        User user = new User();
        model.addAttribute("user", user);
        return "user_create";
    }


    @PostMapping("/create")
    public  String createUser( @Valid UserCreateRequest userCreateRequest,Model model) throws Exception //Creates a new user.
    {
        model.addAttribute("user",userService.createUser(userCreateRequest));

        return "success";
    }

    @GetMapping("/update_form/{user_id}")
    public String editUserForm(@PathVariable String user_id, Model model) //Displays the user update form.
    {
        model.addAttribute("user", userService.getUserById(user_id));
        return "user_edit";
    }


    @PostMapping("/update/{user_id}")
    public  String updateCategory(@PathVariable("user_id") String user_id, @ModelAttribute("user")User user,Model model) throws Exception //Updates an existing user.
    {
        // get user from database by id
        User existingUser = userService.getUserById(user_id);
        existingUser.setUser_id(user_id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPassword(user.getPassword());

        // save updated user object
        userService.updateUser(existingUser);

        return "update";
    }


    @GetMapping("/get/{user_id}")
    public String getUserById(@PathVariable String user_id, Model model) //Retrieves and displays a user by ID.
    {
        model.addAttribute("user", userService.getUserById(user_id));
        return "user_details";
    }

    @GetMapping("/delete/{user_id}")
    public String deleteUserById(@PathVariable String user_id) //Deletes a user by ID.
    {
        userService.deleteUserById(user_id);
        return "delete";
    }

}