package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Repository.CategoryRepo;
import com.WebTechProjekt.Expense_Tracker.util.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryService categoryService;

    private Category testCategory;

    @BeforeEach
    void setUp() {
        testCategory = new Category();
        testCategory.setId(1);
        testCategory.setName("Test Category");
        testCategory.setDescription("Test Desc");
        testCategory.setColor("#9C27B0");
        testCategory.setOwner("Walter");

    }
    @Test
    void testSaveCategory_ShouldSetOwnerAndSave(){

        Category newCategory = new Category();
        newCategory.setName("Test Cat");
        newCategory.setDescription("Test Desc");
        newCategory.setColor("#9C27B0");

        when(categoryRepo.save(any(Category.class))).thenReturn(newCategory);

        try (MockedStatic<SecurityUtils> mockedSecurity = mockStatic(SecurityUtils.class)) {
            mockedSecurity.when(SecurityUtils::getCurrentUsername).thenReturn("currentuser");

            Category result = categoryService.saveCategory(newCategory);

            assertNotNull(result);
            assertEquals("currentuser", newCategory.getOwner());
            verify(categoryRepo, times(1)).save(newCategory);
        }

    }

    @Test
    void testGetAllCategories_ShouldReturnUserCategories() {
        List<Category> mockCategories = List.of(
                testCategory,
                new Category(2,"Category 2","Test Category","#4CAF50","Walter")
        );

    }

}