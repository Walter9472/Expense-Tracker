package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    private static final List<User> users = new ArrayList<>();

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
        for (User storedUser : users) {
            if (storedUser.getUsername() != null && storedUser.getUsername().equals(user.getUsername())) {
                if (encoder.matches(user.getPassword(), storedUser.getPassword())) {
                    return jwtService.generateToken(storedUser.getUsername());
                }
                break;
            }
        }

        throw new BadCredentialsException("Invalid username or password");
    }
}
