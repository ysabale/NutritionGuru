package com.nutrition.service;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.nutrition.model.PersonData;


/**
 * @author aymomin
 *
 */
@SpringBootTest
class NutritionGuruServiceTest {

	@Autowired
	private NutritionGuruService nutritionGuruService;

	
	/**
	 * @implNote test case for the person having stable weight
	 */
	@Test
	void testGetPersonDietTypeForStableWeight() {
		String expectedResult = "Stable Weight";
		PersonData personDetails = new PersonData();
		personDetails.setPersonHeight(170);
		personDetails.setPersonWeight(70);
		String response = nutritionGuruService.getPersonDietType(personDetails);
		assertEquals(expectedResult, response);
	}

	/**
	 * @implNote test case for the person needs to Weight Gain
	 */
	@Test
	void testGetPersonDietTypeForWeightGain() {
		String expectedResult = "Weight Gain";
		PersonData personDetails = new PersonData();
		personDetails.setPersonHeight(170);
		personDetails.setPersonWeight(50);
		String response = nutritionGuruService.getPersonDietType(personDetails);
		assertEquals(expectedResult, response);
	}

	/**
	 * @implNote test case for the person needs to Weight Loss
	 */
	@Test
	void testGetPersonDietTypeForWeightLoss() {
		String expectedResult = "Weight Loss";
		PersonData personDetails = new PersonData();
		personDetails.setPersonHeight(160);
		personDetails.setPersonWeight(100);
		String response = nutritionGuruService.getPersonDietType(personDetails);
		assertEquals(expectedResult, response);
	}
	
	
}
