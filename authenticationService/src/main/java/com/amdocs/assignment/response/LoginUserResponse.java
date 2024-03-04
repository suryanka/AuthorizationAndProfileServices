package com.amdocs.assignment.response;

import org.springframework.beans.factory.annotation.Autowired;

public class LoginUserResponse {
	
	@Autowired
	private UserLogin userLogin;

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	
	
	

}
