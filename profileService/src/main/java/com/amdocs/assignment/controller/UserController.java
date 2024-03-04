package com.amdocs.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.amdocs.assignment.model.UserProfile;
import com.amdocs.assignment.response.PutRequest;
import com.amdocs.assignment.response.UserProfileResponse;
import com.amdocs.assignment.service.UserService;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/assignment")

public class UserController {

	@Autowired
	private final UserService userService;

	// Constructor injection of UserService
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// Endpoint to create a new user profile
	@PostMapping(value = "/register/userProfile", produces = "application/json")
	public UserProfile createUserProfile(@RequestBody UserProfile user) {
		return userService.createUserProfile(user);
	}

	// Endpoint to create a new login user
//	@PostMapping(value = "/register/userLogin", produces = "application/json")
//	public UserLogin createUserLogin(@RequestBody UserLogin user) {
//		return userService.createUserLogin(user);
//	}

	// Endpoint to retrieve all user profiles
	@GetMapping("/register/userProfile")
	public List<UserProfile> getAllUserProfiles() {
		return userService.getAllUserProfiles();
	}

	// Endpoint to retrieve all Login users
//	@GetMapping("/register/userLogin")
//	public List<UserLogin> getAllLoginUsers() {
//		return userService.getAllLoginUsers();
//	}
    
	//Getting a user profile
	@GetMapping("/register/userProfile/{username}")
	public ResponseEntity<UserProfileResponse> getUserProfileByUserName(@PathVariable String username) {
		UserProfile user = userService.getUserProfileByUserName(username);
		UserProfileResponse usrResp = new UserProfileResponse();
		usrResp.setUser(user);
		if (user != null) {

			return ResponseEntity.status(HttpStatus.OK).body(usrResp);
		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usrResp);
		}
	}
    
	//Getting a user login
//	@GetMapping("/register/userLogin/{username}")
//	public ResponseEntity<UserLoginResponse> getUserLoginByUserName(@PathVariable String username) {
//		UserLogin user = userService.getUserLoginByUserName(username);
//		UserLoginResponse usrResp = new UserLoginResponse();
//		usrResp.setUserLogin(user);
//		if (user != null) {
//
//			return ResponseEntity.status(HttpStatus.OK).body(usrResp);
//		} else {
//
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usrResp);
//		}
//	}

	@PutMapping("/profile/{username}")
	public String updateUserByUsername(@PathVariable String username, @RequestBody PutRequest putRequest) {
		return userService.updateUserByUsername(username, putRequest);
	}

	@DeleteMapping("/profile/{username}")
	public String deleteUserProfileByUsername(@PathVariable String username) {
		return userService.deleteUserProfileByUsername(username);
	}
	
//	@DeleteMapping("/login/{username}")
//	public String deleteLoginUserByUsername(@PathVariable String username) {
//		return userService.deleteLoginUserByUsername(username);
//	}

}
