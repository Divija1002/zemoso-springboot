package com.example.springproject.demo.repository;

import com.example.springproject.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
    @Query(value = "select * from employee e where e.userid = ?1",nativeQuery = true)
    public Employee findByUserid(int userid);
}
