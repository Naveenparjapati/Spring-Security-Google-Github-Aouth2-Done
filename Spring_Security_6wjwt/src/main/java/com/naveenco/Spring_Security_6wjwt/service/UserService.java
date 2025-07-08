package com.naveenco.Spring_Security_6wjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.naveenco.Spring_Security_6wjwt.model.Users;
import com.naveenco.Spring_Security_6wjwt.repository.UserRepo;

@Service
public class UserService {
      @Autowired 
	 UserRepo userRepo;
      
      private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
     
      @Autowired
      AuthenticationManager authManager;
      
      @Autowired
      private JWTService jwtservice;
      
	public Users register(Users user) {
   	     user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
    }


	public String verify(Users user) {
		// TODO Auto-generated method stub
		
		Authentication authentication=
				authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated())
			return jwtservice.generateToken(user.getUsername());
		
		return "failure";
	}


	
}
