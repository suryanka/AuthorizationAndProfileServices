package com.amdocs.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.amdocs.assignment.model.UserProfile;



public interface UserProfileRepo extends JpaRepository<UserProfile, Integer >{
	
	UserProfile findByUsername(String username);
	
}
