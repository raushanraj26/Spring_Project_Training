package com.monocept.studentapidemo.controller;

import com.monocept.studentapidemo.exception.StudentNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.studentapidemo.entity.Student;
import com.monocept.studentapidemo.repository.StudentRepository;
import com.monocept.studentapidemo.service.StudentService;
import com.monocept.studentapidemo.service.StudentServiceImplementation;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	// create method
	@PostMapping("/create")
	public Student createStudent(@RequestBody Student s) {
		return studentService.createStudent(s);
	}

	@PostMapping("/createmultiple")
	public List<Student> createMultipleStudent(@RequestBody List<Student> s) {
		return studentService.createMultipleStudent(s);
	}

//	read by id
	@GetMapping("/studentbyid/{id}")
	public Student getStudentById(@PathVariable int id) {
		return studentService.FindById(id);
	}

//	//update student
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody Student updatedstudent) {

		return studentService.updateStudent(id, updatedstudent);
	}

}
