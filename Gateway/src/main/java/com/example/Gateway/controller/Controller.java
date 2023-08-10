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
    @GetMapping("/")
    public ModelAndView dashboard(){
        ModelAndView modelAndView=new ModelAndView("dashboard.html");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView firsPage(){
        ModelAndView modelAndView=new ModelAndView("login.html");
        return modelAndView;
    }
    @GetMapping("/login-action")
    public ModelAndView loginAction(@RequestParam("email") String email,
                                    @RequestParam("password") String password){
            char[] pass=password.toCharArray();
        if (userServices.loginUser(email, pass)){
            return new ModelAndView("redirect:/personalOffice");
        }else {
            return new ModelAndView("redirect:/login");
        }
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
            ModelAndView modelAndView=new ModelAndView("register");
            char[] pass1= password1.toCharArray();
            char[] pass2=password2.toCharArray();
        if (!email.equals("")&& !password1.equals("")&& !password2.equals("") &&
                !firstName.equals("") && !lastName.equals("")) {
            if (pass1.equals(pass2)&&pass1!=userServices.getDEFAULTPASS()&&email.contains("@")){
                userServices.saveUser(email, pass1, firstName, lastName);
            }else {
                //get message or pop up
               return modelAndView.addObject("message", "password 1 and password 2 are not equal");
            }
        }else {
            //get message
           return modelAndView.addObject("message", "please insert all fields");
        }
        return new ModelAndView("redirect:/login");
    }
    //create a personal office
    @GetMapping("/personalOffice")
    public ModelAndView myOffice(){
        ModelAndView modelAndView=new ModelAndView("personalOffice.html");
        return modelAndView;
    }
}
