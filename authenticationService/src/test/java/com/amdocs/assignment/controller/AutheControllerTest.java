package com.amdocs.assignment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.amdocs.assignment.config.JwtAuthenticationFilter;
import com.amdocs.assignment.helper.JwtUtil;
import com.amdocs.assignment.model.UserAuthLogin;
import com.amdocs.assignment.response.PutRequest;
import com.amdocs.assignment.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(autheController.class)
public class AutheControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthService authService;
	
	@Mock
	JwtAuthenticationFilter jwtAuthenticationFilter;
	@Mock
	JwtUtil jwtUtil;
	

	UserAuthLogin user1 = new UserAuthLogin();
	UserAuthLogin user2 = new UserAuthLogin();

	List<UserAuthLogin> userList = new ArrayList<>();
	
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
	
//	@Test
//	void updateDetailsTest() throws Exception
//	{
//		ResponseEntity<String> respEnt =ResponseEntity.ok("Updated Successfully");
//		
//		PutRequest putRequest = new PutRequest("Sample Address","1234567890");
//		 
//		ObjectMapper mapper = new ObjectMapper();
//		String requestJSON = mapper.writeValueAsString(putRequest);
//		
//		when(authService.updateDetails(putRequest)).thenReturn(respEnt);
//		
//		this.mockMvc
//		.perform(put("/assignment/profile")
//				//.with(user("").password(""))
//		.contentType(MediaType.APPLICATION_JSON).content(requestJSON))
//		.andDo(print()).andExpect(status().isOk());
//	}
}
