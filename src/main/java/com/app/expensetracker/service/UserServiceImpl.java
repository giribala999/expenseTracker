package com.app.expensetracker.service;
import com.app.expensetracker.dao.UserRepository;
import com.app.expensetracker.dto.UserCreateRequest;
import com.app.expensetracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public User createUser(UserCreateRequest userCreateRequest) throws Exception {
        User user = userCreateRequest.to();
        User local= this.userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName());

            if(local!= null) {
                System.out.println("User already present!!!");
                throw new Exception("User already present!!!");
            }
            else{
                return userRepository.save(user);
            }
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<User>> getUserById(String user_id){

        Optional<User> user =  userRepository.findById(user_id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(user);
        }

    }

    @Override
    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @Override
    @Transactional
    public User updateUser(String userId, UserCreateRequest userCreateRequest){
        User user = userCreateRequest.to();
        return userRepository.save(user);
    }

    @Override
    @Transactional
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