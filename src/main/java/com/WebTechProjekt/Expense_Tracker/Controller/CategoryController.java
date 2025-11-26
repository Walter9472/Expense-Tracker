package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/et")
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable int id){
        return categoryService.getCategory(id);
    }

    @GetMapping("categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategory();
    }
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(id, category);
        if(updatedCategory == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Update failed: You are not authorized to edit this category or the category does not exist.");
        }
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        if (!categoryService.deleteCategory(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Deletion failed: You are not authorized to delete this category or the category does not exist.");
        }
        return ResponseEntity.noContent().build();
    }


}
