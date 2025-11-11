package com.WebTechProjekt.Expense_Tracker.Service;


import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Entity.UserPrincipal;
import com.WebTechProjekt.Expense_Tracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo repo;
//
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //User user = repo.findByUsername(username);
        User user = null;

        for(User user1 : userService.getAllUsers()){
            if(user1.getUsername().equals(username)){
                user = user1;
                return new UserPrincipal(user);
            }
        }

        System.out.println("User not Found");
        throw new UsernameNotFoundException("User not Found");

    }
}
