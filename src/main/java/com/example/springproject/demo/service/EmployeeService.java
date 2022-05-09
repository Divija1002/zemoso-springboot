package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.EmployeeDto;
import com.example.springproject.demo.entity.Employee;

import java.util.List;

public interface EmployeeService
{
    public List<EmployeeDto> findAll();

    public EmployeeDto findById(int theId);

    public void save(Employee employee);

    public void deleteById(int theId);

    public EmployeeDto findEmployeeByUserid(int id);

    public EmployeeDto entityToDto(Employee employee);

    public Employee dtoToEntity(EmployeeDto employeeDto);
}
