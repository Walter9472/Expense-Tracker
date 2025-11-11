package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user){

        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

}
