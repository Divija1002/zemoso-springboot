package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.EmployeeDto;
import com.example.springproject.demo.entity.Employee;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.repository.EmployeeRepository;
import com.example.springproject.demo.service.EmployeeService;
import com.example.springproject.demo.service.implementation.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeService employeeService=new EmployeeServiceImpl(employeeRepository,modelMapper);

    private Employee employee1;

    private Employee employee2;

    private ArrayList<Employee> list;

    @BeforeEach
    void setMockOutput(){
        Role role=new Role();
        role.setId(1);
        role.setName("ROLE_ADMIN");
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        User user1=new User();
        user1.setId(1);
        user1.setUsername("test1");
        user1.setPassword("test1");
        user1.setRoles(roleSet);
        employee1=new Employee();
        employee1.setId(1);
        employee1.setFirstName("test1");
        employee1.setLastName("test1");
        employee1.setEmail("test1@gmail.com");
        employee1.setPhoneNumber("123456789");
        employee1.setUserid(user1);
        User user2=new User();
        user2.setId(2);
        user2.setUsername("test2");
        user2.setPassword("test2");
        user2.setRoles(roleSet);
        employee2=new Employee();
        employee2.setId(2);
        employee2.setFirstName("test2");
        employee2.setLastName("test2");
        employee2.setEmail("test2@gmail.com");
        employee2.setPhoneNumber("987654321");
        employee2.setUserid(user2);
        list = new ArrayList<Employee>();
        list.add(employee1);
        list.add(employee2);
    }

    @Test
    void findAllTest(){
        when(employeeRepository.findAll()).thenReturn(list);
        List<EmployeeDto> employeeList=employeeService.findAll();
        assertThat(employeeList).hasSize(2);
        assertThat(employeeList.get(0).getEmail()).isEqualTo("test1@gmail.com");
        assertThat(employeeList.get(1).getEmail()).isEqualTo("test2@gmail.com");
    }

    @Test
    void findByIdtest(){
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee1));
        EmployeeDto employeeDto=employeeService.findById(1);
        assertThat(employeeDto.getEmail()).isEqualTo("test1@gmail.com");

        when(employeeRepository.findById(2)).thenReturn(Optional.of(employee2));
        EmployeeDto employeeDto1=employeeService.findById(2);
        assertThat(employeeDto1.getEmail()).isEqualTo("test2@gmail.com");

    }

    @Test
    void findByIdtestFail(){
        assertThrows(RuntimeException.class,()->{
            employeeService.findById(3);
        });
    }

    @Test
    void saveTest(){
        employeeService.save(employee1);
        verify(employeeRepository,times(1)).save(employee1);
    }

    @Test
    void deleteById(){
        employeeService.deleteById(1);
        verify(employeeRepository,times(1)).deleteById(1);
    }
    @Test
    void findCustomerByUseridTest(){
        when(employeeRepository.findByUserid(1)).thenReturn(employee1);
        EmployeeDto employeeDto=employeeService.findEmployeeByUserid(1);
        assertThat(employeeDto.getFirstName()).isEqualTo("test1");
    }

}
