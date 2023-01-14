package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    @Autowired
    BlogRepository blogRepository;

    public void createUser(User user){
        userRepository3.save(user);
    }

    public void deleteUser(int userId){
        try {


            userRepository3.deleteById(userId);
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public void updateUser(User user){
        int userId=user.getId();


        //Blog blog=blogRepository.findById(userId).get();
        User newUser=userRepository3.findById(userId).get();
        if(newUser==null) return;
        if(user.getFirstName()!=null) newUser.setFirstName(user.getFirstName());
        if(user.getLastName()!=null) newUser.setLastName(user.getLastName());
        if(user.getUsername()!=null) newUser.setUsername(user.getUsername());
        if(user.getPassword()!=null) newUser.setPassword(user.getPassword());
        //newUser.setListOfBlogs(user.());
        userRepository3.save(newUser);
    }

    public User findUserByUsername(String username)
    {

        User user;
       try {

           user=userRepository3.findByUsername(username);
       }
       catch (Exception e){
          e.getMessage();
          user=null; // commented by me
       }
       return user;  //written by me
    }
}
