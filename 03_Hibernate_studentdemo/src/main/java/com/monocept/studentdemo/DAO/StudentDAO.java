package com.monocept.studentdemo.DAO;

import com.monocept.studentdemo.model.Student;

public interface StudentDAO {
	public void save(Student thestudent);
	public Student findById(int id);
	
	

}
