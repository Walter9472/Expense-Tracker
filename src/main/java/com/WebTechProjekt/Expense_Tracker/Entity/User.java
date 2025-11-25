package com.WebTechProjekt.Expense_Tracker.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String email;
    private String username;
    private String password;
    private Role role = Role.USER;        // z. B. "USER", "ADMIN"
    private LocalDateTime createdAt = LocalDateTime.now();


    public enum Role{
        USER, ADMIN
    }

}

