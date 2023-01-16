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
            userRepository3.deleteById(userId);
    }

    public void updateUser(User user){
        userRepository3.save(user);
    }

    public User findUserByUsername(String username)
    {
        User user=userRepository3.findByUsername(username);
       return user;  //written by me
    }
}
