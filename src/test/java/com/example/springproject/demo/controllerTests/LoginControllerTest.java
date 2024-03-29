package com.example.springproject.demo.controllerTests;

import com.example.springproject.demo.config.WebSecurityConfig;
import com.example.springproject.demo.controller.LoginController;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.handler.SuccessHandler;
import com.example.springproject.demo.service.CustomerService;
import com.example.springproject.demo.service.RoleService;
import com.example.springproject.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
@ActiveProfiles("test")
@WithMockUser(username = "user1", password = "pwd", roles = "USER")
public class LoginControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void showMyLoginPageTest() throws Exception {
//        this.mockMvc
//                .perform(get("/login"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("fancy-login-new"));
//    }

    @Test
    void showAccessDeniedTest() throws Exception {
        this.mockMvc
                .perform(get("/access-denied"))
                .andExpect(status().isOk())
                .andExpect(view().name("access-denied"));
    }

    @Test
    void showRegistrationFormTest() throws Exception {
        User user=new User();
        this.mockMvc
                .perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user",user))
                .andExpect(view().name("signup"));
    }

//    @Test
//    void processRegisterTest() throws Exception {
//        this.mockMvc
//                .perform(post("/process_register"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("customer",new Customer()))
//                .andExpect(view().name("customer-details"));
//    }



}
