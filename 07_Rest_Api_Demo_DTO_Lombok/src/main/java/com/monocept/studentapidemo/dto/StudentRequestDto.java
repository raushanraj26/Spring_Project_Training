package com.monocept.studentapidemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestDto {
	private String name;
	private int age;
	
//	public StudentRequestDto() {
//		
//	}
//	public StudentRequestDto(String name, int age) {
//		this.name = name;
//		this.age = age;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
//	@Override
//	public String toString() {
//		return "StudentRequestDto [name=" + name + ", age=" + age + "]";
//	}
	

}
