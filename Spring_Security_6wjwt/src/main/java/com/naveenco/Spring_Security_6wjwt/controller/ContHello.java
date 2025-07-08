package com.naveenco.Spring_Security_6wjwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ContHello {
	
	@RequestMapping("/")     //it has 2 object req or res 
    public String greet(HttpServletRequest request) {
        return "Hello World, Welcome to naveenco    "+request.getSession().getId();
    }

}
