package com.app.expensetracker.service;

import com.app.expensetracker.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTransactionImpl {


    @Autowired
    private UserRepository userRepository;



}
