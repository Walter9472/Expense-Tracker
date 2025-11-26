package com.WebTechProjekt.Expense_Tracker.Repository;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

}
