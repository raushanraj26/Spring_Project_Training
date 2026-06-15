package com.monocept.studentapidemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int id;

    @NotBlank(message = "student name cannot be empty")
    @Column(name = "s_name")
    private String name;

    @Min(value = 18, message = "age must be at least 18")
    @Max(value = 100, message = "age must be less than or equal to 100")
    @Column(name = "s_age")
    private int age;

    @NotBlank(message = "department cannot be empty")
    @Column(name = "s_department")
    private String department;

//    // default constructor
//    public Student() {
//    }
//
//    // parameterized constructor
//    public Student(String name, int age, String department) {
//        this.name = name;
//        this.age = age;
//        this.department = department;
//    }
//
//    // getters and setters
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    
//
//    @Override
//    public String toString() {
//        return "Student [id=" + id +
//                ", name=" + name +
//                ", age=" + age +
//                ", department=" + department + "]";
//    }
}