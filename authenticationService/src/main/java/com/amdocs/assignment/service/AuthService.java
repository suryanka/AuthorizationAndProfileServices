package com.amdocs.assignment.service;



import java.util.List;

import org.springframework.http.ResponseEntity;

import com.amdocs.assignment.model.UserAuthLogin;
import com.amdocs.assignment.response.PutRequest;
import com.amdocs.assignment.response.User;
import com.amdocs.assignment.response.UserLogin;

public interface AuthService {
	String login(UserLogin loginUser);

	ResponseEntity<String> updateDetails(PutRequest user);

	ResponseEntity<String> deleteUser();

	ResponseEntity<User> createProfile();

//	UserAuthLogin createUserLogin(UserAuthLogin user);
//
//	List<UserAuthLogin> findAllLoginUsers();

}
