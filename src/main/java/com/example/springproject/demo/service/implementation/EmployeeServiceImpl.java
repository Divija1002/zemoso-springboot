package com.example.springproject.demo.service.implementation;

import com.example.springproject.demo.dto.EmployeeDto;
import com.example.springproject.demo.entity.Employee;
import com.example.springproject.demo.repository.EmployeeRepository;
import com.example.springproject.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,ModelMapper modelMapper)
    {
        this.employeeRepository=employeeRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employeeList=employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList=new ArrayList<>();
        for(Employee tempEmployee:employeeList)
        {
            EmployeeDto employeeDto=entityToDto(tempEmployee);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    @Override
    public EmployeeDto findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee employee;
        if(result.isPresent())
        {
            employee=result.get();
        }
        else
        {
            throw new RuntimeException("employee not found");
        }
        return entityToDto(employee);
    }

    @Override
    public void save(Employee employee)
    {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public EmployeeDto findEmployeeByUserid(int id) {
        Employee employee=employeeRepository.findByUserid(id);
        return entityToDto(employee);
    }

    @Override
    public EmployeeDto entityToDto(Employee employee) {
        EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setUserid(employee.getUserid().getId());
        return employeeDto;
    }

    @Override
    public Employee dtoToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto,Employee.class);
    }
}
