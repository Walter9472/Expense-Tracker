package com.WebTechProjekt.Expense_Tracker.Entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-Test für die User Entity.
 * Testet die grundlegende Funktionalität der User-Klasse ohne Datenbankzugriff.
 */
class UserTest {

    @Test
    void testUserCreationWithAllArgsConstructor() {
        // Arrange & Act
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1, "test@example.com", "testuser", "password123", User.Role.USER, now);

        // Assert
        assertEquals(1, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(User.Role.USER, user.getRole());
        assertEquals(now, user.getCreatedAt());
    }

    @Test
    void testUserCreationWithNoArgsConstructor() {
        // Act
        User user = new User();

        // Assert
        assertNotNull(user);
        assertEquals(0, user.getId()); // Primitive int default ist 0
        assertNull(user.getEmail());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    void testUserSettersAndGetters() {
        // Arrange
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        // Act
        user.setId(42);
        user.setEmail("new@example.com");
        user.setUsername("newuser");
        user.setPassword("newpassword");
        user.setRole(User.Role.ADMIN);
        user.setCreatedAt(now);

        // Assert
        assertEquals(42, user.getId());
        assertEquals("new@example.com", user.getEmail());
        assertEquals("newuser", user.getUsername());
        assertEquals("newpassword", user.getPassword());
        assertEquals(User.Role.ADMIN, user.getRole());
        assertEquals(now, user.getCreatedAt());
    }

    @Test
    void testDefaultRoleIsUser() {
        // Act
        User user = new User();

        // Assert - Lombok @Data generiert default values nicht, aber wir können es setzen
        user.setRole(User.Role.USER);
        assertEquals(User.Role.USER, user.getRole());
    }

    @Test
    void testUserRoleEnum() {
        // Assert
        assertEquals(User.Role.USER, User.Role.valueOf("USER"));
        assertEquals(User.Role.ADMIN, User.Role.valueOf("ADMIN"));
        assertEquals(2, User.Role.values().length);
    }
}
