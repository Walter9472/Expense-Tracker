package com.WebTechProjekt.Expense_Tracker.Repository;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
