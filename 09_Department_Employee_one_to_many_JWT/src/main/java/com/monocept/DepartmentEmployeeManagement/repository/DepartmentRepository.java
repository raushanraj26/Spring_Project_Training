package com.monocept.DepartmentEmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.DepartmentEmployeeManagement.model.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Duplicate department name check while creating
    boolean existsByDepartmentName(String departmentName);

    // Duplicate department name check while updating
    boolean existsByDepartmentNameAndIdNot(String departmentName, Long id);
}