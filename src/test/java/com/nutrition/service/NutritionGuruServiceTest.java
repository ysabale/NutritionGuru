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
	public void testGetPersonDietTypeForStableWeight() {
		String expectedResult = "Stable Weight";
		String response =nutritionGuruService.getPersonDietType(person);
		assertEquals(expectedResult, response);
	}
	@Test
	public void testGetPersonDietTypeForWeightGain() {
		String expectedResult = "Weight Gain";
		PersonData personDetails=new PersonData();
		personDetails.setPersonHeight(170);
		personDetails.setPersonWeight(50);
		String response =nutritionGuruService.getPersonDietType(personDetails);
		assertEquals(expectedResult, response);
	}
	@Test
	public void testGetPersonDietTypeForWeightLoss() {
		String expectedResult = "Weight Loss";
		PersonData personDetails=new PersonData();
		personDetails.setPersonHeight(160);
		personDetails.setPersonWeight(100);
		String response =nutritionGuruService.getPersonDietType(personDetails);
		assertEquals(expectedResult, response);
	}

}
