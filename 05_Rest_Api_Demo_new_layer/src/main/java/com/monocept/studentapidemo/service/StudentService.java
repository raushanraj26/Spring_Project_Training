package com.monocept.studentapidemo.service;


import java.util.List;

import com.monocept.studentapidemo.entity.Student;


public interface StudentService {
	public Student createStudent(Student student);
	public List<Student> createMultipleStudent(List<Student> students);
	public Student FindById(int id);
	public Student updateStudent(int id, Student updatedstudent);
	


}
