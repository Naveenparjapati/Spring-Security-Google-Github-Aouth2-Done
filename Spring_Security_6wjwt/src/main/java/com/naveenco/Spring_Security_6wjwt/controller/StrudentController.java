package com.naveenco.Spring_Security_6wjwt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naveenco.Spring_Security_6wjwt.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StrudentController {
      //we are tring some hard coded values this will display as output
	private List<Student> students=new ArrayList<Student>(List.of(
			
			new Student(1,"navin",90),
			new Student(2,"Kiran",80)
			));
	
	
	@GetMapping("/students")
	public List<Student>  getStudent()
	{
		return students;
	}
	
	//web csrf we are genrating bcz we dont want copy frm browser
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request)
	{
		return (CsrfToken) request.getAttribute("_csrf");
	}
	{
		
	}
	
	
	//we are adding student
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student)
	{
		students.add(student);
		return student;
	}
}  
