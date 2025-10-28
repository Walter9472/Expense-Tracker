package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    // In-Memory Liste für Beispielkategorien
    private static List<Category> categories = new ArrayList<>();

    private final CategoryRepo categoryRepo;

    static {
        categories.add(new Category(1L, "Lebensmittel", "Essen und Trinken", "#FF5733"));
        categories.add(new Category(2L, "Transport", "Fahrten und Öffentliche Verkehrsmittel", "#33FF57"));
        categories.add(new Category(3L, "Freizeit", "Hobby und Unterhaltung", "#3357FF"));
    }


    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category getCategory(Long id)
    {
        Optional<Category> optional = categories.stream().filter(cat -> cat.getId().equals(id)).findFirst();
        return optional.orElse(null);
    }

    public List<Category> getAllCategory() {
        return categories;
    }


    public Category saveCategory(Category category) {
        categories.add(category);
        return category;
    }

    public Category updateCategory(Long id, Category category) {

        for(int i = 0; i < categories.size();i++){
            if(category.getId().equals(id)){
                category.setId(id);
                categories.set(i,category);
                return category;
            }
        }

        return null;
    }

    public void deleteCategory(Long id) {
        categories.removeIf(cat -> cat.getId().equals(id));
    }
}
