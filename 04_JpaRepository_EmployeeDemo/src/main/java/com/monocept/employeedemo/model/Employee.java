package com.monocept.employeedemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empId")
    private int id;

    @Column(name = "empname")
    private String name;

    @Column(name = "empcity")
    private String city;

    @Column(name = "empcitycode")
    private String code;

    @Column(name = "empsalary")
    private double salary;

    @Column(name = "empage")
    private int age;

    @Column(name = "empemail")
    private String email;

    public Employee() {

    }

    public Employee(int id, String name, String city,
                    String code, double salary,
                    int age, String email) {

        this.id = id;
        this.name = name;
        this.city = city;
        this.code = code;
        this.salary = salary;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id +
                ", name=" + name +
                ", city=" + city +
                ", code=" + code +
                ", salary=" + salary +
                ", age=" + age +
                ", email=" + email + "]";
    }
}