package com.monocept.DepartmentEmployeeManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.DepartmentEmployeeManagement.dto.DepartmentRequestDto;
import com.monocept.DepartmentEmployeeManagement.dto.DepartmentResponseDto;
import com.monocept.DepartmentEmployeeManagement.dto.PageResponseDto;
import com.monocept.DepartmentEmployeeManagement.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
//for name is used in swaggerconfig class  SecurityScheme(name = "basicAuth",}
@SecurityRequirement(name = "basicAuth")
public class DepartmentController {
	private DepartmentService departmentservice;

	// create
	@Operation(summary = "Create a new department")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Department created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request") })
	@PostMapping("/create")
	public DepartmentResponseDto createDepartment(@RequestBody DepartmentRequestDto requestdto) {
		return departmentservice.createDepartment(requestdto);
	}

	// read all departments
	@Operation(summary = "Get all departments")
	@ApiResponse(responseCode = "200", description = "Departments fetched successfully")
	@GetMapping("/alldepartments")
	public List<DepartmentResponseDto> getAllDepartments() {
		return departmentservice.getAllDepartments();
	}

	// show all departments with pagination
	@Operation(summary = "Get all departments with pagination")
	@ApiResponse(responseCode = "200", description = "Departments fetched successfully")
	@GetMapping("/page")
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "5") int pageSize) {

		return departmentservice.getAllDepartmentsWithPagination(pageNo, pageSize);
	}

	// get departmet by id
	@Operation(summary = "Get department by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Department found"),
			@ApiResponse(responseCode = "404", description = "Department not found") })
	@GetMapping("/{id}")
	public DepartmentResponseDto getDepartmentById(@PathVariable long id) {
		return departmentservice.getDepartmentById(id);
	}

	// delete department by id
	@Operation(summary = "Delete department by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Department deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Department not found") })
	@DeleteMapping("/{id}")
	public void deleteDepartment(@PathVariable long id) {
		departmentservice.deleteDepartment(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable long id,
			@Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
		
		DepartmentResponseDto updatedDepartment = departmentservice.updateDepartment(id, departmentRequestDto);
		return ResponseEntity.ok(updatedDepartment);
	}

}
