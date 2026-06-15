package com.monocept.studentapidemo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.studentapidemo.dto.PageResponseDto;
import com.monocept.studentapidemo.dto.StudentRequestDto;
import com.monocept.studentapidemo.dto.StudentResponseDto;
import com.monocept.studentapidemo.entity.Student;
import com.monocept.studentapidemo.exception.StudentNotFoundException;
import com.monocept.studentapidemo.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {
	private StudentRepository studentrepository;

//constructor injection
	@Autowired
	public StudentServiceImplementation(StudentRepository studentrepository) {
		this.studentrepository = studentrepository;
	}
	
//	convert dto->student
//	user kuch dedicated fields hi enter krega but jpa ko entity chanheye db me save kr liye
private Student convertToEntity(StudentRequestDto studentrequestdto) {
//	default create krke getter and setter use kree 
//	name and age hi aa rha dto se so these field hi set and get kiye
	Student student=new Student();
	student.setName(studentrequestdto.getName());
	student.setAge(studentrequestdto.getAge());
//	manually save kar rhe hai,not taking input from user because department blank ya null nhi hoa chaheye
	student.setDepartment("General");
	
	return student;
	
}
//convert student-dto
//db jpa ko ek student object return krega but hume show kuch dedicated fields hi show krna hai so 
//		using response dto
private StudentResponseDto convertToDto(Student student) {
//	name,age,department show krna hai,id ko nhi
	StudentResponseDto studentresponsedto=new StudentResponseDto();
	studentresponsedto.setName(student.getName());
	studentresponsedto.setAge(student.getAge());
	studentresponsedto.setDepartment(student.getDepartment());
	return studentresponsedto;
	
	
}

	@Override
	public StudentResponseDto createStudent(StudentRequestDto student) {
//		requetdto->student me change kro,but jpa ek student object return krega
		Student savedStudent= studentrepository.save(convertToEntity(student));  
//		return object to agian dto me change krke show kr do
		return convertToDto(savedStudent);

	}

//	@Override
//	public Student createStudent(Student student) {
//		return studentrepository.save(student);
//		
//	}
//	@Override
//	public List<Student> createMultipleStudent(List<Student> students) {
//		return studentrepository.saveAll(students);
//	}
//
//	@Override
//	public Student FindById(int id) {
//		return studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//	}
//
//	@Override
//	public Student updateStudent(int id, Student updatedstudent) {
//		Student existingstudent = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//
//		existingstudent.setName(updatedstudent.getName());
//		existingstudent.setAge(updatedstudent.getAge());
//		existingstudent.setDepartment(updatedstudent.getDepartment());
//		return studentrepository.save(existingstudent);
//	}
//
//	@Override
//	public Student updatePartially(int id, Map<String, Object> updatedData) {
//		Student existingstudent = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//		// check kon kon sa field aya hai,jo aaya hai usse update kr do
//		if (updatedData.containsKey("name")) {
//			existingstudent.setName((String) updatedData.get("name"));
//
//		}
//		if (updatedData.containsKey("Age")) {
//			existingstudent.setAge((int) updatedData.get("Age"));
//
//		}
//		if (updatedData.containsKey("department")) {
//			existingstudent.setDepartment((String) updatedData.get("department"));
//
//		}
//		return studentrepository.save(existingstudent);
//	}
//
//	@Override
//	public void deleteStudent(int id) {
//		Student existingstudent = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//		studentrepository.delete(existingstudent);
//
//	}

}
