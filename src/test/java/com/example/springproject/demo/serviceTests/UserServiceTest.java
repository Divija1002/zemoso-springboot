package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.UserDto;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.repository.UserRepository;
import com.example.springproject.demo.service.UserService;
import com.example.springproject.demo.service.implementation.UserServiceImpl;
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

@ExtendWith(MockitoExtension.class)
 class UserServiceTest {

    @Mock
     private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService=new UserServiceImpl(userRepository,modelMapper);

    private User user;

    private UserDto userDto;

    private List<User> list;

    @BeforeEach
    void setMockOutput(){
        Role role=new Role();
        role.setId(1);
        role.setName("ROLE_ADMIN");
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        user=new User();
        user.setId(1);
        user.setUsername("test1");
        user.setPassword("test1");
        user.setRoles(roleSet);
        userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        list=new ArrayList<User>();
        list.add(user);
    }

    @Test
    void findAllTest(){
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userRepository.findAll()).thenReturn(list);
        List<UserDto> userList=userService.findAll();
        assertThat(userList).hasSize(1);
        assertThat(userList.get(0).getUsername()).isEqualTo("test1");
    }

    @Test
    void findByIdtest(){
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        UserDto userDto=userService.findById(1);
        assertThat(userDto.getUsername()).isEqualTo("test1");

    }

    @Test
    void findByIdtestFail(){
        assertThrows(RuntimeException.class,()->{
            userService.findById(2);
        });
    }

    @Test
    void saveTest(){
        userService.save(user);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    void deleteById(){
        userService.deleteById(1);
        verify(userRepository,times(1)).deleteById(1);
    }

    @Test
    void findByUsername(){
        when(userRepository.findByUsername("test1")).thenReturn(user);
        assertThat(userService.findByUsername("test1")).isEqualTo(user);
    }

}
