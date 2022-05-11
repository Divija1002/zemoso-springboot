package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.UserDto;
import com.example.springproject.demo.entity.User;

import java.util.List;

public interface UserService
{
    List<UserDto> findAll();

    UserDto findById(int theId);

    void save(User user);

    void deleteById(int theId);

    User findByUsername(String username);

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);
}
