package com.amdocs.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.amdocs.assignment.dao.UserProfileRepo;
import com.amdocs.assignment.model.UserProfile;
import com.amdocs.assignment.response.PutRequest;

@Service
public class UserServiceImpl implements UserService {
	
	private UserProfileRepo userRepository;
	//private UserLoginRepo userLoginRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public UserServiceImpl(UserProfileRepo userRepository) {
		super();
		this.userRepository = userRepository;
		//this.userLoginRepository = userLoginRepository;
	}


	@Override
	public UserProfile createUserProfile(UserProfile use) {
		// TODO Auto-generated method stub
		
		
		UserProfile user= new UserProfile();
		if(getUserProfileByUserName(use.getUsername())!= null) return null;
		//user.setId(use.getId());
		user.setUsername(use.getUsername());
		//user.setPassword(this.passwordEncoder.encode(use.getPassword()));
		user.setPassword(use.getPassword());

		// Saving user to repository
		userRepository.save(user);
		return user;
	}
	
//	@Override
//	public UserLogin createUserLogin(UserLogin use) {
//		// TODO Auto-generated method stub
//		
//		UserLogin user= new UserLogin();
//		user.setId(use.getId());
//		user.setUsername(use.getUsername());
//		//user.setPassword(this.passwordEncoder.encode(use.getPassword()));
//		user.setPassword(use.getPassword());
//
//		// Saving user to repository
//		userLoginRepository.save(user);
//		return user;
//		
//	}




	@Override
	public List<UserProfile> getAllUserProfiles() {
		// TODO Auto-generated method stub
		List<UserProfile> users = userRepository.findAll();
		return users;
	}
	
//	@Override
//	public List<UserLogin> getAllLoginUsers() {
//		// TODO Auto-generated method stub
//		List<UserLogin> users = userLoginRepository.findAll();
//		return users;
//	}



	@Override
	public UserProfile getUserProfileByUserName(String username) {
		// TODO Auto-generated method stub
		UserProfile user = userRepository.findByUsername(username);
		return user;
	}
	
//	@Override
//	public UserLogin getUserLoginByUserName(String username) {
//		// TODO Auto-generated method stub
//		UserLogin user = userLoginRepository.findByUsername(username);
//		return user;
//	}




	@Override
	public String updateUserByUsername(String username, PutRequest putRequest) {
		// TODO Auto-generated method stub
		UserProfile user = userRepository.findByUsername(username);
		//System.out.println("Printing user"+user);
		user.setAddress(putRequest.getAddress());
		user.setPhoneNo(putRequest.getPhoneNo());
		userRepository.save(user);
		return "Updated Successfully";
	}


	@Override
	public String deleteUserProfileByUsername(String username) {
		// TODO Auto-generated method stub
		
		UserProfile user= userRepository.findByUsername(username);
		userRepository.delete(user);
		return "Deleted Successfully";
	}


//	@Override
//	public String deleteLoginUserByUsername(String username) {
//		// TODO Auto-generated method stub
//		UserLogin user= userLoginRepository.findByUsername(username);
//		userLoginRepository.delete(user);
//		return "Deleted Successfully";
//	}

}
