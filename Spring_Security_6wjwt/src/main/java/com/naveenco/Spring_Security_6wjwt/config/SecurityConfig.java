package com.naveenco.Spring_Security_6wjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//in this we are using UserDetailsService for verifing the user
//in this we are using UserDetailsService for verifing the user
//in this we are using UserDetailsService for verifing the user

@Configuration
//i dont want defoult security that provided by spring security
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
	return	http
	  .csrf(customizer -> customizer.disable())
      .authorizeHttpRequests(request -> request
    		  .requestMatchers("register","login")
    		  .permitAll()
    		  .anyRequest().authenticated())
      .httpBasic(Customizer.withDefaults())
      .sessionManagement(session -> 
      session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
      
      .build();
		
		
		
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService); 
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	
//	@Bean
//	public UserDetailsService userDetailsService(){
//		
//		UserDetails user1=User
//				.withDefaultPasswordEncoder()
//				.username("kiran")
//				.password("M@kiran")
//				.roles("USER")
//				.build();
//		
//		
//		UserDetails user2=User
//				.withDefaultPasswordEncoder()
//				.username("Aian")
//				.password("Y@kiran")
//				.roles("ADMIN")
//				.build();
//		
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
//			
	
}
