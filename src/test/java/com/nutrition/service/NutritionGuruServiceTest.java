package com.nutrition.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nutrition.controller.CalorieController;
import com.nutrition.controller.NutritionGuruController;
import com.nutrition.model.PersonData;
import com.nutrition.model.UserCalorieDetails;

@RunWith(PowerMockRunner.class)
public class NutritionGuruServiceTest {

	
	@InjectMocks
	private NutritionGuruService nutritionGuruService;
	
	private PersonData person;
	
	@Before
	public void getPersonDetails() {
		person = new PersonData();
		person.setPersonHeight(170);
		person.setPersonWeight(67);
	}
	
	@Test
	public void testGetPersonDietType() {
		String expectedResult = "Stable Weight";
		String response =nutritionGuruService.getPersonDietType(person);
		assertEquals(expectedResult, response);
	}

}
