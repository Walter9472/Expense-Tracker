package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

//    @Autowired
//    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User());
    }

    public List<User> getAllUsers() {
        return users;
    }


    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        //return userRepo.save(user);
        users.add(user);
        return user;
    }

    public String verify(User user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtService.generateToken(user.getUsername());
        System.out.println(token);
        if(authentication.isAuthenticated())
            return token;

        return "fail";
    }
}
