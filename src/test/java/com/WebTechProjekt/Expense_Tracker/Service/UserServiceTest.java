package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Service-Test für UserService mit Mocks.
 * Testet die Business-Logik isoliert von der Datenbank.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private UserService userService;

    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        encoder = new BCryptPasswordEncoder(12);
    }

    @Test
    void testRegisterUser_ShouldEncodePasswordAndSaveUser() {
        // Arrange
        User inputUser = new User();
        inputUser.setUsername("testuser");
        inputUser.setEmail("test@example.com");
        inputUser.setPassword("plainPassword");

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setUsername("testuser");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encodedPassword");

        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userService.registerUser(inputUser);

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepo, times(1)).save(any(User.class));
        // Passwort sollte verschlüsselt sein (nicht mehr "plainPassword")
        assertNotEquals("plainPassword", inputUser.getPassword());
    }

    @Test
    void testRegisterUser_ShouldNotStorePasswordInPlainText() {
        // Arrange
        User user = new User();
        user.setUsername("secureuser");
        user.setPassword("mySecret123");

        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.registerUser(user);

        // Assert
        assertNotEquals("mySecret123", result.getPassword());
        assertTrue(result.getPassword().startsWith("$2a$")); // BCrypt hash format
    }

    @Test
    void testGetAllUsers_ShouldReturnUserList() {
        // Arrange
        when(userRepo.findAll()).thenReturn(java.util.List.of(
                new User(1, "user1@test.com", "user1", "pass1", User.Role.USER, null),
                new User(2, "user2@test.com", "user2", "pass2", User.Role.ADMIN, null)
        ));

        // Act
        var users = userService.getAllUsers();

        // Assert
        assertEquals(2, users.size());
        verify(userRepo, times(1)).findAll();
    }
}
