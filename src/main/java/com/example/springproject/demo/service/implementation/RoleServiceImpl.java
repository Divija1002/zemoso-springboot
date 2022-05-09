package com.example.springproject.demo.service.implementation;

import com.example.springproject.demo.dto.RoleDto;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.repository.RoleRepository;
import com.example.springproject.demo.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService
{
    private  RoleRepository roleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,ModelMapper modelMapper)
    {
        this.roleRepository=roleRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public List<RoleDto> findAll() {
        List<Role> roleList=roleRepository.findAll();
        List<RoleDto> roleDtoList=new ArrayList<>();
        for(Role tempRole:roleList)
        {
            RoleDto roleDto=entityToDto(tempRole);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }

    @Override
    public Role findById(int theId) {
        Optional<Role> result = roleRepository.findById(theId);
        Role role;
        if(result.isPresent())
        {
            role=result.get();
        }
        else
        {
            throw new RuntimeException("role not found");
        }
        return role;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteById(int theId) {
        roleRepository.deleteById(theId);
    }

    @Override
    public RoleDto entityToDto(Role role) {
        RoleDto roleDto=modelMapper.map(role,RoleDto.class);
        return roleDto;
    }

    @Override
    public Role dtoToEntity(RoleDto roleDto) {
        Role role=modelMapper.map(roleDto,Role.class);
        return role;
    }
}
