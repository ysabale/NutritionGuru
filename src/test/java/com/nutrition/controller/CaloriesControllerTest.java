package com.nutrition.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.nutrition.authentication.JwtTokenUtil;
import com.nutrition.exception.NutritionCustomException;
import com.nutrition.model.UserCalorieDetails;
import com.nutrition.service.CalorieService;
import com.nutrition.service.LoginServiceImpl;

@RunWith(PowerMockRunner.class)
public class CaloriesControllerTest {

	@InjectMocks
	CalorieController calorieController;

	@Mock
	private CalorieService calorieService;
	
	@Mock
	LoginServiceImpl loginService;

	
	UserCalorieDetails userCalorieDetails = null;
	
	@Before
	public void getUserCalorieDetails() {
		userCalorieDetails = new UserCalorieDetails();
		userCalorieDetails.setAge(31);
		userCalorieDetails.setGender("F");
		userCalorieDetails.setExcercise(1);
		userCalorieDetails.setHeight(150.3f);
		userCalorieDetails.setWeight(75.00f);
	}

	@Test
	public void getCalorieDetails() throws NutritionCustomException {
		Float expectedResult = 100.0f;
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
		.loadUserByUsername("Test@gmail.com");
		when(calorieService.calculateBMR(userCalorieDetails)).thenReturn(expectedResult);
		calorieController.getCalorieDetails(userCalorieDetails);
		Float response = calorieController.getCalorieDetails(userCalorieDetails);
		assertEquals(expectedResult, response);
	}
	
	public static String getToken() {
		UserDetails userDetails = new User("pitambar2@gmail.com", "xyz12345", new ArrayList<>());
		String jwt = new JwtTokenUtil().generateToken(userDetails);
		return jwt;
	}

}