package com.WebTechProjekt.Expense_Tracker.Repository;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
