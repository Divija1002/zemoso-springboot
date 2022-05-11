package com.example.springproject.demo.service.implementation;

import com.example.springproject.demo.dto.UserDto;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.repository.UserRepository;
import com.example.springproject.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper)
    {
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> userList=userRepository.findAll();
        List<UserDto> userDtoList=new ArrayList<>();
        for(User tempUser:userList)
        {
            UserDto userDto=entityToDto(tempUser);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(int theId)
    {
        Optional<User> result = userRepository.findById(theId);
        User user;
        if(result.isPresent())
        {
            user=result.get();
        }
        else
        {
            throw  new RuntimeException("user not found id: "+theId);
        }
        return entityToDto(user);
    }

    @Override
    public void save(User user)
    {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto entityToDto(User user)
    {
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public User dtoToEntity(UserDto userDto)
    {
        return modelMapper.map(userDto,User.class);
    }
}
