package com.example.Gateway.services;

import com.example.Gateway.dao.UserDAO;
import com.example.Gateway.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    //using a default password because string contruction new object in heap, but I get char, and after I use, overwrite
    final char[] DEFAULTPASS={'0', '0', '0', '0'};
    public char[] getDEFAULTPASS() {
        return DEFAULTPASS;
    }

    @Autowired
    UserDAO userDAO;

    public void saveUser(String email, char[] password, String firstName, String lastName){
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userDAO.save(user);
    }
    public boolean loginUser(String email, char[] password){
        User user=userDAO.findByEmail(email);
        if (user.getPassword().equals(password)){
            user.setPassword(DEFAULTPASS);
            return true;
        }
        else{
            user.setPassword(DEFAULTPASS);
            return false;
        }
    }
}
