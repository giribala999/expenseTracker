//package com.app.expensetracker.service;
//
//import com.app.expensetracker.dao.UserRepository;
//import com.app.expensetracker.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private UserRepository userRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository theUserRepository) {
//        userRepository = theUserRepository;
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User findById(String theId) {
//        Optional<User> result = userRepository.findById(theId);
//
//        User theUser = null;
//
//        if (result.isPresent()) {
//            theUser = result.get();
//        }
//        else {
//            // we didn't find the employee
//            throw new RuntimeException("Did not find User id - " + theId);
//        }
//
//        return theUser;
//    }
//
//    @Override
//    public void save(User theEmployee) {
//        userRepository.save(theEmployee);
//    }
//
//    @Override
//    public void deleteById(String theId) {
//        userRepository.deleteById(theId);
//    }
//
//}
//
