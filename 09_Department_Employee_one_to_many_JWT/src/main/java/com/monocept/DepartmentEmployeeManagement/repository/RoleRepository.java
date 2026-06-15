package com.monocept.DepartmentEmployeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.DepartmentEmployeeManagement.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
