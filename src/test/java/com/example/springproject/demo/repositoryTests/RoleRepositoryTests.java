package com.example.springproject.demo.repositoryTests;

import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.repository.RoleRepository;
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
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    @Order(1)
    @Commit
    void saveRoleTest()
    {
        Role role=new Role();
        role.setName("ROLE_CUSTOMER");
        roleRepository.save(role);
        Assertions.assertThat(role.getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void getRoleTest()
    {
        Role role=roleRepository.findById(1).get();
        Assertions.assertThat(role.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void getListOfRoleTest()
    {
        List<Role> roleList=roleRepository.findAll();
        Assertions.assertThat(roleList.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void updateRoleTest()
    {
        Role role=roleRepository.findById(1).get();
        role.setName("ROLE_ADMIN");
        Role updatedrole=roleRepository.findById(1).get();
        Assertions.assertThat(updatedrole.getName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    @Order(5)
    void deleteRoleTest()
    {
        roleRepository.deleteById(1);
        Optional<Role> result=roleRepository.findById(1);
        Role role=null;
        if(result.isPresent())
        {
            role=result.get();
        }
        Assertions.assertThat(role).isNull();
    }
}
