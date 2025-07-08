package com.naveenco.Spring_Security_6wjwt.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.naveenco.Spring_Security_6wjwt.service.JWTService;
import com.naveenco.Spring_Security_6wjwt.service.MyUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtFilter extends OncePerRequestFilter{
   
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String autHeader=request.getHeader("Autorization");
		String token=null;
		String username=null;
		
		if(autHeader!=null  && autHeader.startsWith("Bearer "))
		{
			token=autHeader.substring(7);
			username=jwtService.extractUserName(token);
		}
		
		if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=context.getBean(MyUserDetailService.class).loadUserByUsername(username);
			
			if(jwtService.validateToken(token,userDetails))
			{
				UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

	
	
}
