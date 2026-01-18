package com.WebTechProjekt.Expense_Tracker.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void testCategoryCreationWithAllArgsConstructor() {
        // Arrange & Act
        Category category = new Category(
                1,
                "Lebensmittel",
                "Einkäufe im Supermarkt, Bäcker, Metzger etc.",
                "#4CAF50",
                "Walter"
        );

        // Assert
        assertEquals(1, category.getId());
        assertEquals("Lebensmittel", category.getName());
        assertEquals("Einkäufe im Supermarkt, Bäcker, Metzger etc.", category.getDescription());
        assertEquals("#4CAF50", category.getColor());
        assertEquals("Walter", category.getOwner());
    }

    @Test
    void testCategoryCreationWithNoArgsConstructor() {
        // Act
        Category category = new Category();

        // Assert
        assertNotNull(category);
        assertNull(category.getId());
        assertNull(category.getName());
        assertNull(category.getDescription());
        assertNull(category.getColor());
        assertNull(category.getOwner());
    }

    @Test
    void testCategorySettersAndGetters() {
        // Arrange
        Category category = new Category();

        // Act
        category.setId(42);
        category.setName("Freizeit");
        category.setDescription("Freizeitaktivitäten");
        category.setColor("#FF5722");
        category.setOwner("Walter");

        // Assert
        assertEquals(42, category.getId());
        assertEquals("Freizeit", category.getName());
        assertEquals("Freizeitaktivitäten", category.getDescription());
        assertEquals("#FF5722", category.getColor());
        assertEquals("Walter", category.getOwner());
    }
}