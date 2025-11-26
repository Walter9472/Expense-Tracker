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

    // In-Memory Liste für Beispielkategorien
//    private static List<Category> categories = new ArrayList<>();



    private final CategoryRepo categoryRepo;

//    static {
//        categories.add(new Category(1, "Lebensmittel", "Essen und Trinken", "#FF5733",null));
//        categories.add(new Category(2, "Transport", "Fahrten und Öffentliche Verkehrsmittel", "#33FF57",null));
//        categories.add(new Category(3, "Freizeit", "Hobby und Unterhaltung", "#3357FF",null));
//        categories.add(new Category(4, "Gesundheit", "Medizinische Ausgaben und Vorsorge", "#FF33AA",null));
//    }


    @Autowired
    public CategoryService(CategoryRepo categoryRepo) { this.categoryRepo = categoryRepo;}
//
//
//
//    public Category getCategory(Long id)
//    {
//        Optional<Category> optional = categories.stream().filter(cat -> cat.getId().equals(id)).findFirst();
//        return optional.orElse(null);
//    }
//
//    public List<Category> getAllCategory() {
//        String owner = SecurityUtils.getCurrentUsername();
//
//        return categories.stream()
//                .filter(cat -> Objects.equals(cat.getOwner(), owner))
//                .collect(Collectors.toList());
//    }
//
//
//    public Category saveCategory(Category category) {
//        category.setOwner(SecurityUtils.getCurrentUsername());
//        categories.add(category);
//        return category;
//    }
//
//    public Category updateCategory(Long id, Category payload) {
//        String owner = SecurityUtils.getCurrentUsername();
//        for(int i = 0; i < categories.size();i++){
//            Category current = categories.get(i);
//            if(Objects.equals(current.getId(), id)){
//                if (!Objects.equals(current.getOwner(), owner)) {
//                    return null;
//                }
//                Category merged = new Category(
//                        id,
//                        payload.getName(),
//                        payload.getDescription(),
//                        payload.getColor(),
//                        owner
//                );
//
//                categories.set(i, merged);
//                return merged;
//            }
//        }
//        return null;
//    }
//
//    public boolean deleteCategory(Long id) {
//        String owner = SecurityUtils.getCurrentUsername();
//        return categories.removeIf(cat -> Objects.equals(cat.getId(), id) && Objects.equals(cat.getOwner(), owner));
//    }

    public Category getCategory(int id)
    {

//        Optional<Category> optional = categories.stream().filter(cat -> cat.getId().equals(id)).findFirst();
        return categoryRepo.findById(id).orElse(null);
    }

    public List<Category> getAllCategory() {
        String owner = SecurityUtils.getCurrentUsername();
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .filter(cat -> Objects.equals(cat.getOwner(), owner))
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
