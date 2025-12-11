package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Repository.CategoryRepo;
import com.WebTechProjekt.Expense_Tracker.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {


    private final CategoryRepo categoryRepo;



    @Autowired
    public CategoryService(CategoryRepo categoryRepo) { this.categoryRepo = categoryRepo;}


    public Category getCategory(int id)
    {

//        Optional<Category> optional = categories.stream().filter(cat -> cat.getId().equals(id)).findFirst();
        return categoryRepo.findById(id).orElse(null);
    }

    public List<Category> getAllCategory() {
        String owner = SecurityUtils.getCurrentUsername();
        List<Category> categories = categoryRepo.findAll();
        String d = "default";
        return categories.stream()
                .filter(cat -> Objects.equals(cat.getOwner(), owner) || Objects.equals(cat.getOwner(),d))
                .collect(Collectors.toList());
    }


    public Category saveCategory(Category category) {
        category.setOwner(SecurityUtils.getCurrentUsername());
        categoryRepo.save(category);
        return category;
    }

    public Category updateCategory(int id, Category payload) {
        String owner = SecurityUtils.getCurrentUsername();

        if(Objects.equals(payload.getId(),id) && Objects.equals(payload.getOwner(),owner)) {
            return categoryRepo.save(payload);
        }else
            return null;
    }

    public boolean deleteCategory(int id) {
        String owner = SecurityUtils.getCurrentUsername();
        Category category = categoryRepo.findById(id).orElse(null);
        if(Objects.equals(category.getId(),id) && Objects.equals(category.getOwner(),owner)) {
            categoryRepo.delete(category);
            return true;
        } else
            return false;
    }
}
