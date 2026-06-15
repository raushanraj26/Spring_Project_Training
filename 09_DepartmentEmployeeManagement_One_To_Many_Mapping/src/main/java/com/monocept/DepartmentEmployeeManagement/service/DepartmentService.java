package com.monocept.DepartmentEmployeeManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.monocept.DepartmentEmployeeManagement.dto.DepartmentRequestDto;
import com.monocept.DepartmentEmployeeManagement.dto.DepartmentResponseDto;
import com.monocept.DepartmentEmployeeManagement.dto.PageResponseDto;

public interface DepartmentService {

	// Create Department with Employees
	DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);

	// Get All Departments
	List<DepartmentResponseDto> getAllDepartments();

	// Get Departments with Pagination
	PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNo, int pageSize);

	// Get Department By Id
	DepartmentResponseDto getDepartmentById(Long id);

	// Update Department and Employees
	DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto departmentRequestDto);

	// Delete Department
	void deleteDepartment(Long id);
}