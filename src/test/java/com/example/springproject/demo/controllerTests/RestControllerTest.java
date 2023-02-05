package com.example.springproject.demo.controllerTests;

import com.example.springproject.demo.config.WebSecurityConfig;
import com.example.springproject.demo.controller.RestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(RestController.class)
@ActiveProfiles("test")
@WithMockUser(username = "user1", password = "pwd", roles = "USER")
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userDashboardTest() throws Exception {
        this.mockMvc
                .perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"));
    }

    @Test
    void userAdminTest() throws Exception {
        this.mockMvc
                .perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));;
    }
}

