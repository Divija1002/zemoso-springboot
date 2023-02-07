package com.example.springproject.demo.repositoryTests;

import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.repository.RoleRepository;
import com.example.springproject.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    @Order(1)
    @Commit
    void saveUserTest()
    {
        Role role=new Role();
        role.setName("ROLE_CUSTOMER");
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        roleRepository.save(role);
        User user=new User();
        user.setUsername("test1");
        user.setPassword("test1");
        user.setRoles(roleSet);
        userRepository.save(user);
        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void getUserTest()
    {
        User user=userRepository.findById(1).get();
        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void getListOfUserTest()
    {
        List<User> userList=userRepository.findAll();
        Assertions.assertThat(userList.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void updateUserTest()
    {
        User user=userRepository.findById(1).get();
        user.setUsername("test3");
        User updatedUser=userRepository.findById(1).get();
        Assertions.assertThat(updatedUser.getUsername()).isEqualTo("test3");
    }

    @Test
    @Order(5)
    void deleteUserTest()
    {
        userRepository.deleteById(1);
        Optional<User> result=userRepository.findById(1);
        User user=null;
        if(result.isPresent())
        {
            user=result.get();
        }
        Assertions.assertThat(user).isNull();
    }

    @Test
    @Order(6)
    void findByUsernameTest(){
        Assertions.assertThat(Optional.of(userRepository.findByUsername("test1"))).isEqualTo(userRepository.findById(1));
    }


}
