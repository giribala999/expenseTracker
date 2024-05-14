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
    public User authenticateUser(String firstName,String lastName, String password) {
// Implement authentication logic here, e.g., fetching user by username and password from database
        return  userRepository.findByFirstNameAndLastNameAndPassword(firstName,lastName,password);
    }

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
    public User getUserById(String user_id){
        return userRepository.findById(user_id).get();
    }

    @Override
    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @Override
    @Transactional
    public User updateUser(User user)throws Exception{
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