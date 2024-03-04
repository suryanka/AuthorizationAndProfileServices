package com.amdocs.assignment.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.amdocs.assignment.model.UserAuthLogin;

@DataJpaTest
public class UserAuthLoginRepoTest {
	
	@Autowired
	public UserAuthLoginRepo uerAuthLoginRepo;

	UserAuthLogin userAuthLogin;

	@BeforeEach
	void setUp() {

		userAuthLogin = new UserAuthLogin();
		userAuthLogin.setId(1);
		userAuthLogin.setUsername("Ram");
		userAuthLogin.setPassword("Ram");
		uerAuthLoginRepo.save(userAuthLogin);
	}

	@AfterEach
	void tearDown() {

		userAuthLogin = null;
		uerAuthLoginRepo.deleteAll();
	}
	
	// TestCase Success

		@Test
		void testFindByUserName_Found() {

			UserAuthLogin user1 = uerAuthLoginRepo.findByUsername("Ram");

			assertThat(user1.getId()).isEqualTo(userAuthLogin.getId());
			assertThat(user1.getUsername()).isEqualTo(userAuthLogin.getUsername());
		}

		// TestCase Failure
		@Test
		void testFindByUserName_NotFound() {
			UserAuthLogin user1 = uerAuthLoginRepo.findByUsername("Rohan2");
			assertThat(user1 == null).isTrue();

		}
}
