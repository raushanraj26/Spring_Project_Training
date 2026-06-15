//only difference in previous project (05_RESt_api_new_layer)  is is project dto add kr rhe jo input/output ko restrict kjrega
//DTO stands for Data Transfer Object.
//It is used to transfer data between different layers of an application.
//DTO sends only the required data instead of the complete entity object.
//It helps in improving security by hiding sensitive data like passwords.
//DTO reduces unnecessary data transfer between backend and frontend.
//It makes API responses clean and easy to manage.
//DTO is commonly used between Controller, Service, and Database layers in Spring Boot.
//There are mainly two types of DTOs:
//Request DTO → used to take data from client
//Response DTO → used to send data to client
//DTO helps in separating internal database structure from API response structure.
//In simple words, DTO is a filtered object that carries only useful information.
package com.monocept.studentapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
