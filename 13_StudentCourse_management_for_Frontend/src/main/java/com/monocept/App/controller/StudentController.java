package com.monocept.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.App.entity.Student;
import com.monocept.App.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("http://localhost:5173/")
@RequiredArgsConstructor
public class StudentController {

	
	private final StudentService studentService;

	@PostMapping("/add-student")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}

	@GetMapping("/get-all-student")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}/student")
	public Student getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@PutMapping("/{id}/update")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {

		return studentService.updateStudent(id, student);
	}

	@DeleteMapping("/{id}/delete")
	public String deleteStudent(@PathVariable Long id) {

		studentService.deleteStudent(id);

		return "Student deleted successfully";
	}
}