package com.WebTechProjekt.Expense_Tracker.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //@Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;        // z. B. "USER", "ADMIN"
    private LocalDateTime createdAt = LocalDateTime.now();


    public enum Role{
        USER, ADMIN
    }

}

