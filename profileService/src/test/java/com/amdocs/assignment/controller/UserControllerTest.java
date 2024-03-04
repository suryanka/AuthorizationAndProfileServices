package com.amdocs.assignment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.amdocs.assignment.model.UserProfile;
import com.amdocs.assignment.response.PutRequest;
import com.amdocs.assignment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	UserProfile user1 = new UserProfile();
	UserProfile user2 = new UserProfile();

	List<UserProfile> userList = new ArrayList<>();

	@BeforeEach
	void setUp() {
		user1.setId(1);
		user1.setUsername("Ankit1");
		user1.setPassword("Ankit");

		user2.setId(2);
		user2.setUsername("Animesh2");
		user2.setPassword("Animesh");

		userList.add(user1);
		userList.add(user2);

	}

	@AfterEach
	void tearDown() {
		user1 = null;
		user2 = null;
	}

	@Test
	void createUserProfileTest() throws Exception {
		// Mapping user1 object to JSON
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(user1);

		// Mocking userService to return user1
		when(userService.createUserProfile(any(UserProfile.class))).thenReturn(user1);

		// Performing POST request and validating response
		this.mockMvc.perform(
				post("/assignment/register/userProfile").contentType(MediaType.APPLICATION_JSON).content(requestJSON))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	void getAllUserProfilestest() throws Exception {

		// Mocking the userService to return userList
		when(userService.getAllUserProfiles()).thenReturn(userList);

		// Performing GET request and validating response
		this.mockMvc.perform(get("/assignment/register/userProfile")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void getUserProfileByUserNameTest() throws Exception {

		//Mocking the userservice to return user
		when(userService.getUserProfileByUserName(any(String.class))).thenReturn(user1);
		
		
		this.mockMvc
				.perform(get("/assignment/register/userProfile/Ankit1"))
				.andDo(print()).andExpect(status().isOk());
	}
	
	
	@Test
	void updateUserByUsernameTest() throws Exception {
		
		when(userService.updateUserByUsername(any(String.class), any(PutRequest.class)))
		.thenReturn("Updated Successfully");
		
		 PutRequest putRequest = new PutRequest("Sample Address","1234567890");
		 
		 ObjectMapper mapper = new ObjectMapper();
			String requestJSON = mapper.writeValueAsString(putRequest);
	        
		
		this.mockMvc
		.perform(put("/assignment/profile/Ankit1")
		.contentType(MediaType.APPLICATION_JSON).content(requestJSON))
		.andDo(print()).andExpect(status().isOk());
		
	}
	
	@Test
	void deleteUserProfileByUsernameTest() throws Exception
	{
		when(userService.deleteUserProfileByUsername(any(String.class)))
		.thenReturn("Deleted Successfully");
		
		this.mockMvc
		.perform(delete("/assignment/profile/Ankit1"))
		.andDo(print()).andExpect(status().isOk());
	}

}
