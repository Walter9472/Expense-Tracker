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

/**
 * Service-Klasse für die Verwaltung von Kategorien.
 * Ermöglicht das Erstellen, Abrufen, Aktualisieren und Löschen von Ausgabenkategorien.
 */
@Service
public class CategoryService {


    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) { this.categoryRepo = categoryRepo;}

    /**
     * Ruft eine einzelne Kategorie anhand ihrer ID ab.
     * @param id Die ID der Kategorie.
     * @return Die Kategorie oder null, falls nicht gefunden.
     */
    public Category getCategory(int id)
    {
        return categoryRepo.findById(id).orElse(null);
    }

    /**
     * Ruft alle Kategorien ab, die entweder dem aktuellen Benutzer gehören oder als "default" markiert sind.
     * @return Liste der zugreifbaren Kategorien.
     */
    public List<Category> getAllCategory() {
        String owner = SecurityUtils.getCurrentUsername();
        List<Category> categories = categoryRepo.findAll();
        String d = "default";
        return categories.stream()
                .filter(cat -> Objects.equals(cat.getOwner(), owner) || Objects.equals(cat.getOwner(), d))
                .collect(Collectors.toList());
    }

    /**
     * Speichert eine neue Kategorie und setzt den aktuellen Benutzer als Besitzer.
     * @param category Die zu speichernde Kategorie.
     * @return Die gespeicherte Kategorie.
     */
    public Category saveCategory(Category category) {
        category.setOwner(SecurityUtils.getCurrentUsername());
        categoryRepo.save(category);
        return category;
    }

    /**
     * Aktualisiert eine bestehende Kategorie, sofern sie dem aktuellen Benutzer gehört.
     * @param id Die ID der Kategorie.
     * @param payload Die neuen Daten der Kategorie.
     * @return Die aktualisierte Kategorie oder null bei fehlender Berechtigung.
     */
    public Category updateCategory(int id, Category payload) {
        String owner = SecurityUtils.getCurrentUsername();

        if(Objects.equals(payload.getId(), id) && Objects.equals(payload.getOwner(), owner)) {
            return categoryRepo.save(payload);
        } else
            return null;
    }

    /**
     * Löscht eine Kategorie anhand ihrer ID, sofern sie dem aktuellen Benutzer gehört.
     * @param id Die ID der zu löschenden Kategorie.
     * @return true, wenn das Löschen erfolgreich war.
     */
    public boolean deleteCategory(int id) {
        String owner = SecurityUtils.getCurrentUsername();
        Category category = categoryRepo.findById(id).orElse(null);
        if(category != null && Objects.equals(category.getId(), id) && Objects.equals(category.getOwner(), owner)) {
            categoryRepo.delete(category);
            return true;
        } else
            return false;
    }
}
