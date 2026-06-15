package com.monocept.studentapidemo.controller;
import com.monocept.studentapidemo.exception.StudentNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.studentapidemo.entity.Student;
import com.monocept.studentapidemo.repository.StudentRepository;

@RestController
@RequestMapping("api/student")
@CrossOrigin(value = "http://localhost:5174/")
public class StudentController {
	private StudentRepository repository;
	
	@Autowired
	public StudentController(StudentRepository repository) {
		
		this.repository = repository;
	}
	
	
	//create method
	@PostMapping("/create")
	public Student createStudent(@RequestBody Student s) {
		return repository.save(s);
	}
	
	@PostMapping("/createmultiple")
	public List<Student> createmultipleStudent(@RequestBody List<Student> s) {
		return repository.saveAll(s);
	}
	
	
	//read all
	@GetMapping("/allstudent")
	public List<Student> getAllStudent(){
		return repository.findAll();
	}
	
//	read by id
	@GetMapping("/studentbyid/{id}")
	public Optional<Student> getStudentById(@PathVariable int id)  {
	    return repository.findById(id);
	}
	
	//update student
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable int id,@RequestBody Student updatedstudent){
		Optional<Student> existingstudent=repository.findById(id);
		if(existingstudent==null) {
			throw new StudentNotFoundException(id);
		}
		Student s=existingstudent.get();
		s.setName(updatedstudent.getName());
		s.setAge(updatedstudent.getAge());
		s.setDepartment(updatedstudent.getDepartment());
		return repository.save(s);
	}
	
//	update student partially(only dedicated field update
	@PatchMapping("{id}")
	public Student updatePartially(@PathVariable int id,@RequestBody Map<String,Object> updatedData) {
		Student existingstudent=repository.findById(id).orElseThrow(()->new StudentNotFoundException(id));
		//check kon kon sa field aya hai,jo aaya hai usse update kr do
		if(updatedData.containsKey("name")) {
			existingstudent.setName((String) updatedData.get("name"));
			
		}
		if(updatedData.containsKey("Age")) {
			existingstudent.setAge((int) updatedData.get("Age"));
			
		}
		if(updatedData.containsKey("department")) {
			existingstudent.setDepartment((String) updatedData.get("department"));
			
		}
		return repository.save(existingstudent);

		
	}
	
	
	//delete Student by id
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable int id) {
		Student existingstudent=repository.findById(id).orElseThrow(()->new StudentNotFoundException(id));
		repository.delete(existingstudent);
	}
	
	
	
	
	

}
