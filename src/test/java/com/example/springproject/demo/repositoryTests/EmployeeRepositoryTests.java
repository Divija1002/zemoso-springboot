package com.example.springproject.demo.repositoryTests;

import com.example.springproject.demo.entity.Employee;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.repository.EmployeeRepository;
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
public class EmployeeRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @Order(1)
    @Commit
    void saveEmployeeTest()
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
        Employee employee=new Employee();
        employee.setFirstName("test2");
        employee.setLastName("test2");
        employee.setEmail("test2@gmail.com");
        employee.setPhoneNumber("987654321");
        employee.setUserid(user);
        employeeRepository.save(employee);
        Assertions.assertThat(employee.getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void getEmployeeTest()
    {
        Employee employee=employeeRepository.findById(1).get();
        Assertions.assertThat(employee.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void getListOfEmployeeTest()
    {
        List<Employee> employeeList=employeeRepository.findAll();
        Assertions.assertThat(employeeList.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void updateEmployeeTest()
    {
        Employee employee=employeeRepository.findById(1).get();
        employee.setEmail("test3@gmail.com");
        Employee updatedEmployee=employeeRepository.findById(1).get();
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("test3@gmail.com");
    }

    @Test
    @Order(5)
    void deleteEmployeeTest()
    {
        employeeRepository.deleteById(1);
        Optional<Employee> result=employeeRepository.findById(1);
        Employee employee=null;
        if(result.isPresent())
        {
            employee=result.get();
        }
        Assertions.assertThat(employee).isNull();
    }

    @Test
    @Order(6)
    void findByUseridTest(){
        Assertions.assertThat(Optional.of(employeeRepository.findByUserid(1))).isEqualTo(employeeRepository.findById(1));
    }
}
