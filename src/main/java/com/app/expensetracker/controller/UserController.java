package com.app.expensetracker.controller;
import com.app.expensetracker.entity.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/controller")
public class UserController {
    HashMap<String,User> map = new HashMap<>();
    private final List<User> theUsers = new ArrayList<>();
    String firstName,lastName;
    HashMap<String, CatTransResponse> user_trans_map = new HashMap<>();

    CatTransResponse catTran;
    double balance;
    @GetMapping("/user")
    public List<User> getUser() {

        System.out.println("GETMAPPING 1  "+theUsers);
        return theUsers;
    }

    @GetMapping("/user/{user_id}")
    public User getUser(@PathVariable String user_id) {
        System.out.println("GETMAPPING   id:  "+user_id+"---- user  :"+map.get(user_id));
        if (map.containsKey(user_id)) {
            return map.get(user_id);
        }
        else {
            throw new UserNotFoundException("User id : '" +user_id+"' not found");
        }
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user){
       UUID uuid= UUID.randomUUID();
        String user_id=uuid.toString();
        firstName=user.getFirstName();
        lastName=user.getLastName();

        catTran=user_trans_map.get(user_id);

        List<Double> trans;
        if(catTran!=null) {

            trans=catTran.getTrans();
            for(int i=0; i< trans.size();i++){
                balance=balance+ trans.get(i);
            }
        }

        user=new User(user_id,firstName, lastName ,catTran, balance);
        theUsers.add(user);

        map.put(user_id,user);

        return "ID :"+user_id+"...... User :"+user;
    }

    @PutMapping("/user/{user_id}")
    public String updateUser(@PathVariable String user_id, @RequestBody User user) {
        //theUsers.set(pos-1,new User(user.getId(),user.getFirstName(), user.getLastName()));
        theUsers.remove(getUser(user_id));

        firstName=user.getFirstName();
        lastName=user.getLastName();
        catTran=user_trans_map.get(user_id);
        List<Double> trans=catTran.getTrans();
        for(int i=0; i< trans.size();i++){
            balance=balance+ trans.get(i);
        }
        user=new User(user_id,firstName, lastName ,catTran, balance);
        theUsers.add(user);

        map.put(user_id,user);

        return "User updated";
    }

    @DeleteMapping("/user/{user_id}")
    public String deleteUser(@PathVariable String user_id ) {
        theUsers.remove(getUser(user_id));
        map.remove(user_id);
        return "User deleted";
    }


}

