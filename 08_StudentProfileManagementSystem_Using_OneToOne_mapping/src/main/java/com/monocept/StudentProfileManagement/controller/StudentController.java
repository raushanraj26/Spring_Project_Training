package com.monocept.StudentProfileManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.StudentProfileManagement.dto.StudentRequestDto;
import com.monocept.StudentProfileManagement.dto.StudentResponseDto;
import com.monocept.StudentProfileManagement.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/student")
public class StudentController {
	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	// create method
	@PostMapping("/create")
	public StudentResponseDto createStudent(@RequestBody StudentRequestDto s) {
		return studentService.createStudent(s);
	}
	
	// create method
		@GetMapping("/allstudents")
		public List<StudentResponseDto> getAllStudents () {
			return studentService.getAllStudents();
		}
		
}
