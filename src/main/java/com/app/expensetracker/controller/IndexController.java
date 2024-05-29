package com.app.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
    public class IndexController {

        @GetMapping("/")
        public String index() //Handles GET requests to the root URL ("/") and returns the welcome view.
        {
            return "welcome";
        }

    }
