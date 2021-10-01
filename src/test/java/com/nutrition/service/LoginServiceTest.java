package com.nutrition.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nutrition.model.LoginDetails;
import com.nutrition.repository.LoginRepository;

@SpringBootTest
public class LoginServiceTest {

	@MockBean
	LoginRepository loginRepository;

	@Autowired
	LoginServiceImpl userService;

	private LoginDetails loginDetails;

	@BeforeEach
	void initUserObject() {
		loginDetails = new LoginDetails();
		loginDetails.setEmailId("pitambar2@gmail.com");
		loginDetails.setUsername("Test");
		loginDetails.setPassword("xyz12345");
		loginDetails.setConfPassword("xyz12345");
		loginDetails.setGender("M");

	}

	@Test
	void testAuthenticate() {
		LoginDetails login = new LoginDetails();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(loginDetails.getPassword());
		login.setPassword(hashedPassword);
		login.setEmailId("pitambar4@gmail.com");
		login.setUsername("Test");
		doReturn(login).when(loginRepository).findByEmailId(any(String.class));
		LoginDetails userDetails = userService.signIn(loginDetails);
		assertEquals("Test", userDetails.getUsername());
	}

	@Test
	void testCreateUser() {
		doReturn(loginDetails).when(loginRepository).save(any(LoginDetails.class));
		LoginDetails userDetails = userService.signUp(loginDetails);
		assertEquals("Test", userDetails.getUsername());
		assertNotNull(userDetails.getEmailId());
	}

	@Test
	void testLoadUserByName() {
		doReturn(loginDetails).when(loginRepository).findByEmailId(any(String.class));
		UserDetails appUser = userService.loadUserByUsername("pitambar2@gmail.com");
		assertEquals(loginDetails.getEmailId(), appUser.getUsername());
		assertEquals(loginDetails.getPassword(), appUser.getPassword());
	}
}
