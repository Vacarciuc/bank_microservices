package com.example.Gateway.controller;


import com.example.Gateway.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserServices userServices;
    //using rest for API
    @GetMapping("")
    public ModelAndView firsPage(){
        ModelAndView modelAndView=new ModelAndView("login.html");
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView registerUser(){
        ModelAndView modelAndView=new ModelAndView("register.html");
        return modelAndView;
    }
    @PostMapping("/register-action")
    public ModelAndView registerAction(@RequestParam("email") String email,
                                       @RequestParam("password1") String password1,
                                       @RequestParam("password2") String password2,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName){
        if (!email.equals("")&& !password1.equals("")&& !password2.equals("") &&
                !firstName.equals("") && !lastName.equals("")) {
            if (password1.equals(password2)){
                userServices.saveUser(email, password1, firstName, lastName);
            }else {
                //get message or pop up

            }
        }else {
            //get message
        }
        return new ModelAndView("redirect:/personalOffice");
    }
}
