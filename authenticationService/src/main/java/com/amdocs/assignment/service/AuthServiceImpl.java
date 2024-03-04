package com.amdocs.assignment.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amdocs.assignment.config.JwtAuthenticationFilter;
import com.amdocs.assignment.dao.UserAuthLoginRepo;
import com.amdocs.assignment.model.UserAuthLogin;
import com.amdocs.assignment.response.LoginUserResponse;
import com.amdocs.assignment.response.PutRequest;
import com.amdocs.assignment.response.User;
import com.amdocs.assignment.response.UserLogin;

@Service
@Primary
public class AuthServiceImpl implements AuthService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private String returnedUserUsername;
	private String returnedUserPassword; 	
	private Integer returnedUserId;
	
	//private String globalUserName = jwtAuthenticationFilter.getGlobalUsername();
	
	private UserAuthLoginRepo userAuthLoginRepo;
	
	public AuthServiceImpl(UserAuthLoginRepo userAuthLoginRepo) {
		super();
		this.userAuthLoginRepo = userAuthLoginRepo;
	}


	@Override
	public String login(UserLogin loginUser) {
		// TODO Auto-generated method stub
		LoginUserResponse returnedUser = returnUser(loginUser.getUsername());
		System.out.println(returnedUser.getUserLogin());
		if (returnedUser != null) {
			// Verifying password
			String password = loginUser.getPassword();
			// String encodedPassword = returnedUser.getUser().getPassword();

			// Boolean isPassWordRight = passwordEncoder.matches(password, encodedPassword);

			Boolean isPassWordRight = password.equals(returnedUser.getUserLogin().getPassword());

			// If password matches, returning successful login response
			if (isPassWordRight) {
				returnedUserUsername=returnedUser.getUserLogin().getUsername();
				returnedUserPassword=returnedUser.getUserLogin().getPassword();
				returnedUserId=returnedUser.getUserLogin().getId();
				return "Successfully logged in";

			} else {

				return "Wrong Password";
			}
		} else {

			return "Usename not exists";
		}

	}

	public LoginUserResponse returnUser(String username) {
		//return restTemplate.getForObject("http://localhost:9090/assignment/register/userLogin/" + username,
		//		LoginUserResponse.class);
		
		UserAuthLogin user= userAuthLoginRepo.findByUsername(username);
		

		
		//System.out.println("Printing User : "+ user);
		UserLogin userLogin = new UserLogin(user.getId(), user.getUsername(),
				user.getPassword());
		LoginUserResponse loginUsrResp= new LoginUserResponse();
		loginUsrResp.setUserLogin(userLogin);
		return loginUsrResp;
	}
	

	@Override
	public ResponseEntity<String> updateDetails(PutRequest putRequest) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> requestEntity = new HttpEntity<Object>(putRequest, headers);

		return restTemplate.exchange(
				"http://localhost:9090/assignment/profile/" + jwtAuthenticationFilter.getGlobalUsername(),
				HttpMethod.PUT, requestEntity, String.class);

	}

	@Override
	public ResponseEntity<String> deleteUser() {
		// TODO Auto-generated method stub

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

		return restTemplate.exchange(
				"http://localhost:9090/assignment/profile/" + jwtAuthenticationFilter.getGlobalUsername(),
				HttpMethod.DELETE, requestEntity, String.class);
	}

	@Override
	public ResponseEntity<User> createProfile() {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		UserLogin registerDetails= new UserLogin(returnedUserId, returnedUserUsername, returnedUserPassword);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(registerDetails, headers);

		return restTemplate.exchange(
				"http://localhost:9090/assignment/register/userProfile" ,
				HttpMethod.POST, requestEntity, User.class);
	}


//	@Override
//	public UserAuthLogin createUserLogin(UserAuthLogin user) {
//		// TODO Auto-generated method stub
//		userAuthLoginRepo.save(user);
//		return user;
//	}
//
//
//	@Override
//	public List<UserAuthLogin> findAllLoginUsers() {
//		// TODO Auto-generated method stub
//		List<UserAuthLogin> users = userAuthLoginRepo.findAll();
//		return users;
//	}

}
