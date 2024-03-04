package com.amdocs.assignment.service;

import java.util.List;


import com.amdocs.assignment.model.UserProfile;
import com.amdocs.assignment.response.PutRequest;

public interface UserService {

	UserProfile createUserProfile(UserProfile user);

	List<UserProfile> getAllUserProfiles();


	String updateUserByUsername(String username, PutRequest putRequest);

	String deleteUserProfileByUsername(String username);

	UserProfile getUserProfileByUserName(String username);

	//UserLogin getUserLoginByUserName(String username);

	//List<UserLogin> getAllLoginUsers();

	//UserLogin createUserLogin(UserLogin user);

   //String deleteLoginUserByUsername(String username);

}
