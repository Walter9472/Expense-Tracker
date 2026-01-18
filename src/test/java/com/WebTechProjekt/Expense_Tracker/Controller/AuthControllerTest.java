package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Service.JWTService;
import com.WebTechProjekt.Expense_Tracker.Service.MyUserDetailsService;
import com.WebTechProjekt.Expense_Tracker.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import com.WebTechProjekt.Expense_Tracker.Config.SecurityConfig;
import com.WebTechProjekt.Expense_Tracker.Config.JwtFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller-Test für AuthController.
 * Testet die REST-Endpunkte mit MockMvc.
 */
@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, JwtFilter.class})
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private UserService userService;

    @SuppressWarnings("removal")
    @MockBean
    private JWTService jwtService;

    @SuppressWarnings("removal")
    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @Test
    void testRegister_ShouldReturnCreatedUser() throws Exception {
        // Arrange
        User inputUser = new User();
        inputUser.setUsername("newuser");
        inputUser.setEmail("new@example.com");
        inputUser.setPassword("password123");

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setUsername("newuser");
        savedUser.setEmail("new@example.com");

        when(userService.registerUser(any(User.class))).thenReturn(savedUser);

        // Act & Assert
        mockMvc.perform(post("/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("newuser"))
                .andExpect(jsonPath("$.email").value("new@example.com"));
    }

    @Test
    void testLogin_WithValidCredentials_ShouldReturnToken() throws Exception {
        // Arrange
        User loginUser = new User();
        loginUser.setUsername("testuser");
        loginUser.setPassword("correctPassword");

        String mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.test.token";
        when(userService.verify(any(User.class))).thenReturn(mockToken);

        // Act & Assert
        mockMvc.perform(post("/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isOk())
                .andExpect(content().string(mockToken));
    }

    @Test
    void testLogin_WithInvalidCredentials_ShouldReturnUnauthorized() throws Exception {
        // Arrange
        User loginUser = new User();
        loginUser.setUsername("testuser");
        loginUser.setPassword("wrongPassword");

        when(userService.verify(any(User.class)))
                .thenThrow(new BadCredentialsException("Invalid username or password"));

        // Act & Assert
        mockMvc.perform(post("/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isUnauthorized());
    }
}

