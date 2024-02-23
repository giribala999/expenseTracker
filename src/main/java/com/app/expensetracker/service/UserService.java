package com.app.expensetracker.service;

import com.app.expensetracker.dao.UserRepository;
import com.app.expensetracker.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){

        userRepository.save(user);
    }
    public ResponseEntity<Optional<User>> getUserById(String user_id){

        Optional<User> user =  userRepository.findById(user_id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(user);
        }

    }
    public void updateUser(User user){
      userRepository.save(user);
    }

    public ResponseEntity<Optional<User>> deleteUserById(String user_id){
        Optional<User> user =  userRepository.findById(user_id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        else{
            userRepository.deleteById(user_id);
            return ResponseEntity.ok(user);
        }
    }
}
