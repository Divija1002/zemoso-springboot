package com.example.springproject.demo.repository;

import com.example.springproject.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer>
{

}
