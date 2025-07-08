package com.naveenco.Spring_Security_6wjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naveenco.Spring_Security_6wjwt.model.Users;
@Repository
public interface UserRepo extends JpaRepository<Users,Integer > {

	Users findByUsername(String username);

	

}
