package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.EmployeeDto;
import com.example.springproject.demo.entity.Employee;

import java.util.List;

public interface EmployeeService
{
    List<EmployeeDto> findAll();

    EmployeeDto findById(int theId);

    void save(Employee employee);

    void deleteById(int theId);

    EmployeeDto findEmployeeByUserid(int id);

    EmployeeDto entityToDto(Employee employee);

    Employee dtoToEntity(EmployeeDto employeeDto);
}
