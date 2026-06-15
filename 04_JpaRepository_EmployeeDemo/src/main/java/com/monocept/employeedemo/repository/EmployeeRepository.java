package com.monocept.employeedemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.employeedemo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	public List<Employee> findByName(String Name);
	
	
	

}
