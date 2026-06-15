package com.monocept.App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.App.entity.Student;
import com.monocept.App.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(Student student) {
		//only foreign key set kr rhe hai
		student.getCourses().forEach(course -> course.setStudent(student));
		return studentRepository.save(student); // cascade saves courses
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long id) {

		return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
	}

	@Override
	public Student updateStudent(Long id, Student student) {

		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		existingStudent.setName(student.getName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setMobile(student.getMobile());
		existingStudent.setDepartment(student.getDepartment());

		return studentRepository.save(existingStudent);
	}

	@Override
	public void deleteStudent(Long id) {

		Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

		studentRepository.delete(student);
	}
}