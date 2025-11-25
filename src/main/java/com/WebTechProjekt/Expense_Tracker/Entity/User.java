package com.WebTechProjekt.Expense_Tracker.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private int id;
    private String email;
    private String username;
    private String password;
    private Role role = Role.USER;        // z. B. "USER", "ADMIN"
    private LocalDateTime createdAt = LocalDateTime.now();


    public enum Role{
        USER, ADMIN
    }

}

