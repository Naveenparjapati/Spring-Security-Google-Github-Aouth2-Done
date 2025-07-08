package com.naveenco.Spring_Security_6wjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naveenco.Spring_Security_6wjwt.model.UserPrincipal;
import com.naveenco.Spring_Security_6wjwt.model.Users;
import com.naveenco.Spring_Security_6wjwt.repository.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user=userRepo.findByUsername(username);
		if(user==null)
		{
			System.out.println("user not found");
			throw new UsernameNotFoundException("user not found");
		}
		//	UserDetails is a ionterface need implimentation class

		
		return new UserPrincipal(user);
	}

}
