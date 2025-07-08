package com.naveenco.Spring_Security_6wjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naveenco.Spring_Security_6wjwt.model.Users;
import com.naveenco.Spring_Security_6wjwt.service.UserService;

@RestController
public class UseerController {
     
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
    	 return userService.register(user);
     }
     
	@PostMapping("/login")
	public String login(@RequestBody  Users user)
	{
		
		return userService.verify(user);
	}
}
