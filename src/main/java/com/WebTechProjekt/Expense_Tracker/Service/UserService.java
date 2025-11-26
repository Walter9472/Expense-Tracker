package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

//    private static final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);

    }

    public String verify(User user) {
    Optional<User> userOpt = userRepo.findByUsername(user.getUsername());

    if (userOpt.isEmpty()){
        throw new BadCredentialsException("Invalid username or password");
    }

    User storedUser = userOpt.get();

    if(!encoder.matches(user.getPassword(),storedUser.getPassword())){
        throw new BadCredentialsException("Invalid username or password");


    }
        // Token erstellen für erfolgreich authentifizierten Benutzer
        return jwtService.generateToken(storedUser.getUsername());
    }
}
