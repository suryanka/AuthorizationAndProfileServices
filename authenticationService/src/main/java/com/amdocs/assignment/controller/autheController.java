package com.amdocs.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.assignment.helper.JwtUtil;
import com.amdocs.assignment.model.UserAuthLogin;
import com.amdocs.assignment.response.Response;
import com.amdocs.assignment.response.User;
import com.amdocs.assignment.response.UserLogin;
import com.amdocs.assignment.response.UserResponse;
import com.amdocs.assignment.response.PutRequest;
import com.amdocs.assignment.service.AuthService;
import com.amdocs.assignment.service.CustomUserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "http://localhost:9091")
@RestController
@RequestMapping("/assignment")
public class autheController {

	@Autowired
	private final AuthService authService;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	public autheController(AuthService authService) {
		super();
		this.authService = authService;
	}

	// Endpoint to create a new login user
//	@PostMapping(value = "/register", produces = "application/json")
//	public UserAuthLogin createUserLogin(@RequestBody UserAuthLogin user) {
//		return authService.createUserLogin(user);
//	}
	
//	@GetMapping(value = "/registeredUsers", produces = "application/json")
//	public List<UserAuthLogin> findAllLoginUsers()
//	{
//		return authService.findAllLoginUsers();
//	}

	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<Response> login(@RequestBody UserLogin loginUser) throws JsonProcessingException {

		String response = authService.login(loginUser);

		if (response.equals("Successfully logged in")) {
			UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(loginUser.getUsername());
			String token = jwtUtil.generateToken(userDetails);
			String res = response + " Token: " + token;
			return ResponseEntity.ok(new Response(res));
		} else if (response.equals("Wrong Password"))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(response));
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(response));

	}

	@PostMapping(value = "/profile", produces = "application/json")
	public ResponseEntity<UserResponse> createProfile() {
		ResponseEntity<User> user = authService.createProfile();
		UserResponse usrResp = new UserResponse();
		usrResp.setUser(user.getBody());

		if (user.getBody() != null) {
			return ResponseEntity.ok(usrResp);
		} else {
			System.out.println("Printing Null user");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usrResp);
		}

	}

	@GetMapping(value = "/welcome")
	public String welcome() {
		return "welcome";
	}

	@PutMapping(value = "/profile", produces = "application/json")
	public ResponseEntity<Response> updateDetails(@RequestBody PutRequest putRequest) {
		Response response = new Response("");
		ResponseEntity<String> respEnt = authService.updateDetails(putRequest);
		response.setMessage(respEnt.getBody());
		return ResponseEntity.status(respEnt.getStatusCode()).body(response);

	}

	@DeleteMapping(value = "/profile", produces = "application/json")

	public ResponseEntity<Response> deleteUser() {
		Response response = new Response("");
		ResponseEntity<String> respEnt = authService.deleteUser();
		response.setMessage(respEnt.getBody());
		return ResponseEntity.status(respEnt.getStatusCode()).body(response);
	}

}
