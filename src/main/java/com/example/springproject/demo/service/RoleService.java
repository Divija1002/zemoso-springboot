package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.RoleDto;
import com.example.springproject.demo.entity.Role;

import java.util.List;

public interface RoleService
{
    List<RoleDto> findAll();

    Role findById(int theId);

    void save(Role role);

    void deleteById(int theId);

    RoleDto entityToDto(Role role);

    Role dtoToEntity(RoleDto roleDto);
}
