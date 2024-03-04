package com.amdocs.assignment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amdocs.assignment.dao.UserProfileRepo;
import com.amdocs.assignment.model.UserProfile;
import com.amdocs.assignment.response.PutRequest;

public class UserServiceImplTest {
	
	
	private UserService userService ;
	
	@Mock
    private UserProfileRepo userRepository;	
	
	AutoCloseable autoCloseable;
	UserProfile userProfile;
	
	
	@BeforeEach
	void setUp()
	{
		autoCloseable = MockitoAnnotations.openMocks(this);
		
		userProfile = new UserProfile();
		userProfile.setId(1);
		userProfile.setUsername("Ankit1");
		userProfile.setPassword("Ankit");
		
		userService = Mockito.spy( new UserServiceImpl(userRepository));
	}
	
	
	@AfterEach
	void tearDown() throws Exception
	{
		autoCloseable.close();
	}
	
	@Test
	void createUserProfileTest() {
		
		
		//Saving userProfile to userRepository
		when(userRepository.save(any(UserProfile.class))).thenReturn(userProfile);
		
		UserProfile usrPrf = userService.createUserProfile(userProfile);
		assertThat(usrPrf.getUsername()).isEqualTo(userProfile.getUsername());
		assertThat(usrPrf.getPassword()).isEqualTo(userProfile.getPassword());
	}
	
	
	@Test
	void getUserProfileByUserNametest() {
		when(userRepository.findByUsername(any(String.class))).thenReturn(userProfile);
		UserProfile usrPrf = userService.getUserProfileByUserName(userProfile.getUsername());
		assertThat(usrPrf.getUsername()).isEqualTo(userProfile.getUsername());
		assertThat(usrPrf.getPassword()).isEqualTo(userProfile.getPassword());
	}
	
	@Test
	void updateUserByUsernameTest()
	{
		when(userRepository.save(any(UserProfile.class))).thenReturn(userProfile);
		when(userRepository.findByUsername(any(String.class))).thenReturn(userProfile);
		
		PutRequest putRequest = new PutRequest(null, null);
		
		assertThat(userService.updateUserByUsername(userProfile.getUsername(), putRequest)).isEqualTo("Updated Successfully");
		
	}
	
	@Test
	void getAllUserProfilesTest()
	{
		when(userRepository.findAll()).thenReturn(new ArrayList<UserProfile>(Collections.singleton(userProfile)));

		// Asserting the result
		assertThat(userService.getAllUserProfiles().get(0).getUsername()).isEqualTo(userProfile.getUsername());
		assertThat(userService.getAllUserProfiles().get(0).getPassword()).isEqualTo(userProfile.getPassword());
	}
	
	@Test
	void deleteUserProfileByUsername()
	{
		when(userRepository.findByUsername(any(String.class))).thenReturn(userProfile);
		doAnswer(Answers.CALLS_REAL_METHODS).when(userService).deleteUserProfileByUsername(any(String.class));
		assertThat(userService.deleteUserProfileByUsername(userProfile.getUsername())).isEqualTo("Deleted Successfully");
	}
	

}
