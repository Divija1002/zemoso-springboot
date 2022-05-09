package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.dto.UserDto;
import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.entity.User;

import java.util.List;

public interface UserService
{
    public List<UserDto> findAll();

    public UserDto findById(int theId);

    public void save(User user);

    public void deleteById(int theId);

    User findByUsername(String username);

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);
}
