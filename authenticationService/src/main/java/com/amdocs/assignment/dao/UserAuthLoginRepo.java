package com.amdocs.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.assignment.model.UserAuthLogin;

public interface UserAuthLoginRepo extends JpaRepository<UserAuthLogin, Integer>{
	
	UserAuthLogin  findByUsername(String username);
}
