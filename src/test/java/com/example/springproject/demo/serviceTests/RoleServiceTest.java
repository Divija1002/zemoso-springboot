package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.RoleDto;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.repository.RoleRepository;
import com.example.springproject.demo.service.RoleService;
import com.example.springproject.demo.service.implementation.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RoleService roleService=new RoleServiceImpl(roleRepository,modelMapper);

    private Role role1;

    private RoleDto roleDto1;

    private Role role2;

    private RoleDto roleDto2;

    private List<Role> list;

    @BeforeEach
    void setMockOutput(){
        role1=new Role();
        role1.setId(1);
        role1.setName("ROLE_ADMIN");
        role2=new Role();
        role2.setId(2);
        role2.setName("ROLE_CUSTOMER");
        roleDto1=new RoleDto(1,"ROLE_ADMIN");
        roleDto2=new RoleDto(2,"ROLE_CUSTOMER");
        list=new ArrayList<>();
        list.add(role1);
        list.add(role2);
    }

    @Test
    void findAllTest(){
        when(modelMapper.map(role1, RoleDto.class)).thenReturn(roleDto1);
        when(modelMapper.map(role2, RoleDto.class)).thenReturn(roleDto2);
        when(roleRepository.findAll()).thenReturn(list);
        List<RoleDto> roleList=roleService.findAll();
        assertThat(roleList).hasSize(2);
        assertThat(roleList.get(0).getName()).isEqualTo("ROLE_ADMIN");
        assertThat(roleList.get(1).getName()).isEqualTo("ROLE_CUSTOMER");
    }

    @Test
    void findByIdtest(){
        when(roleRepository.findById(1)).thenReturn(Optional.of(role1));
        Role role=roleService.findById(1);
        assertThat(role.getName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    void findByIdtestFail(){
        assertThrows(RuntimeException.class,()->{
            roleService.findById(3);
        });
    }

    @Test
    void saveTest(){
        roleService.save(role1);
        verify(roleRepository,times(1)).save(role1);
    }

    @Test
    void deleteById(){
        roleService.deleteById(1);
        verify(roleRepository,times(1)).deleteById(1);
    }

}
