package com.example.Gateway.services;

import com.example.Gateway.dao.UserDAO;
import com.example.Gateway.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    UserDAO userDAO;

    public void saveUser(String email, String password, String firstName, String lastName){
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userDAO.save(user);
    }
    public boolean loginUser(String email, String password){
        User user=userDAO.findByEmail(email);
        if (user.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
}
