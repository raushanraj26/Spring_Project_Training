package com.monocept.studentapidemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.studentapidemo.entity.Student;
import com.monocept.studentapidemo.exception.StudentNotFoundException;
import com.monocept.studentapidemo.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {
private  StudentRepository studentrepository;

//constructor injection
@Autowired
	public StudentServiceImplementation(StudentRepository studentrepository) {
	this.studentrepository = studentrepository;
}

	@Override
	public Student createStudent(Student student) {
		return studentrepository.save(student);
		
	}

	@Override
	public List<Student> createMultipleStudent(List<Student> students) {
		return studentrepository.saveAll(students);
	}

	@Override
	public Student FindById(int id) {
		return studentrepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException( id));
	}

	@Override
	public Student updateStudent(int id,Student updatedstudent) {
		Student existingstudent=studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException( id));
		
		
		existingstudent.setName(updatedstudent.getName());
		existingstudent.setAge(updatedstudent.getAge());
		existingstudent.setDepartment(updatedstudent.getDepartment());
		return studentrepository.save(existingstudent) ;
	}

}
