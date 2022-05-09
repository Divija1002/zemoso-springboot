package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.RoleDto;
import com.example.springproject.demo.entity.Role;

import java.util.List;

public interface RoleService
{
    public List<RoleDto> findAll();

    public Role findById(int theId);

    public void save(Role role);

    public void deleteById(int theId);

    public RoleDto entityToDto(Role role);

    public Role dtoToEntity(RoleDto roleDto);
}
