package com.monocept.studentdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")  //use for changing table name bt by defauls same as class name
public class Student {
	
	@Id  //identifies primary key
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="full_name")
	private String name;
	
	
	 // Default constructor REQUIRED by Hibernate
    public Student() {
    }

    
	public Student(int id,String name) {
		this.id=id;
		this.name=name;
		
	}
	
	public int getid() {
		return id;
	}
	public String  getname() {
		return name;
	}

}
