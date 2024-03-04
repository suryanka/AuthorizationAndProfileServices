package com.amdocs.assignment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.amdocs.assignment.config.JwtAuthenticationFilter;
import com.amdocs.assignment.dao.UserAuthLoginRepo;
import com.amdocs.assignment.model.UserAuthLogin;
import com.amdocs.assignment.response.LoginUserResponse;
import com.amdocs.assignment.response.UserLogin;

public class AuthServiceImplTest {
	
	private AuthService authService;
	@Mock
	private UserAuthLoginRepo userAuthLoginRepo;
	@Mock
    private RestTemplate restTemplate;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	AutoCloseable autoCloseable;
	UserAuthLogin userAuthLogin;
	
	@BeforeEach
	void setUp()
	{
		autoCloseable = MockitoAnnotations.openMocks(this);
		
		userAuthLogin = new UserAuthLogin();
		userAuthLogin.setId(1);
		userAuthLogin.setUsername("Ankit1");
		userAuthLogin.setPassword("Ankit");
		
		authService = Mockito.spy( new AuthServiceImpl(userAuthLoginRepo));
		jwtAuthenticationFilter= new JwtAuthenticationFilter();
		jwtAuthenticationFilter.setGlobalUsername(userAuthLogin.getUsername());
	}
	
	
	@AfterEach
	void tearDown() throws Exception
	{
		autoCloseable.close();
	}
	
//	@Test
//	void returnUserTest()
//	{
//		when(userAuthLoginRepo.findByUsername(any(String.class)))
//		.thenReturn(userAuthLogin);
//		
//		UserLogin userLogin = new UserLogin(userAuthLogin.getId(), userAuthLogin.getUsername(),
//				userAuthLogin.getPassword());
//		LoginUserResponse loginUsrResp= new LoginUserResponse();
//		loginUsrResp.setUserLogin(userLogin);
//		
//		LoginUserResponse lus = authService.re
//		
//		
//	}
	
	@Test
	void loginTest()
	{
		when(userAuthLoginRepo.findByUsername(any(String.class)))
		.thenReturn(userAuthLogin);
		
		UserLogin userLogin = new UserLogin(userAuthLogin.getId(), userAuthLogin.getUsername(), userAuthLogin.getPassword());
		
		assertThat(authService.login(userLogin)).isEqualTo("Successfully logged in");
	}
	
//	@Test
//	void deleteUserTest()
//	{
//		ResponseEntity<String> rsp = ResponseEntity.ok( "Deleted Successfully");
//	
//		//when(jwtAuthenticationFilter.getGlobalUsername()).thenReturn(userAuthLogin.getUsername());
//		when(restTemplate.exchange(any(String.class),  any(HttpMethod.class), any(HttpEntity.class), eq(String.class)))
//		.thenReturn(rsp);
//		
//	
//		
//		assertThat(authService.deleteUser()).isEqualTo(rsp);
//	}
	
//	@Test
//	void createUserLoginTest()
//	{
//		when(userAuthLoginRepo.save(any(UserAuthLogin.class))).thenReturn(userAuthLogin);
//		UserAuthLogin usr = authService.createUserLogin(userAuthLogin);
//		
//		assertThat(usr.getUsername()).isEqualTo(userAuthLogin.getUsername());
//	}
	
}
