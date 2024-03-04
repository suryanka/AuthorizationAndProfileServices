package com.amdocs.assignment.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.amdocs.assignment.model.UserProfile;

@DataJpaTest
public class UserProfileRepoTest {

	@Autowired
	public UserProfileRepo userProfileRepo;

	UserProfile userProfile;

	@BeforeEach
	void setUp() {

		userProfile = new UserProfile();
		userProfile.setId(1);
		userProfile.setUsername("Ram");
		userProfile.setPassword("Ram");
		userProfileRepo.save(userProfile);
	}

	@AfterEach
	void tearDown() {

		userProfile = null;
		userProfileRepo.deleteAll();
	}

	// TestCase Success

	@Test
	void testFindByUserName_Found() {

		UserProfile user1 = userProfileRepo.findByUsername("Ram");

		assertThat(user1.getId()).isEqualTo(userProfile.getId());
		assertThat(user1.getUsername()).isEqualTo(userProfile.getUsername());
	}

	// TestCase Failure
	@Test
	void testFindByUserName_NotFound() {
		UserProfile user1 = userProfileRepo.findByUsername("Rohan2");
		assertThat(user1 == null).isTrue();

	}
}
