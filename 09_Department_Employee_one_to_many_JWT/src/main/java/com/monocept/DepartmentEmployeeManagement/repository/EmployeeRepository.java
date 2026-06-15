package com.monocept.DepartmentEmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.DepartmentEmployeeManagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Duplicate email check while creating
	boolean existsByEmail(String email);

	// Duplicate email check while updating
	boolean existsByEmailAndIdNot(String email, Long id);
}