package com.amdocs.assignment.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amdocs.assignment.dao.UserAuthLoginRepo;
import com.amdocs.assignment.response.LoginUserResponse;


@Service
public class CustomUserDetailsServiceImpl extends AuthServiceImpl implements UserDetailsService{
	
	public CustomUserDetailsServiceImpl(UserAuthLoginRepo userAuthLoginRepo) {
		super(userAuthLoginRepo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		LoginUserResponse returnedUser= returnUser(username);
		if(returnedUser.getUserLogin().getUsername().equals(username))
		{
			return new User(returnedUser.getUserLogin().getUsername(), returnedUser.getUserLogin().getPassword(), 
					new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found!");
		}
		
	}
}
