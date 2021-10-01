package com.nutrition.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nutrition.model.Diet;
import com.nutrition.model.Food;
import com.nutrition.model.PersonData;
import com.nutrition.repository.DietRepository;
import com.nutrition.repository.FoodRepository;

@SpringBootTest
public class DietServiceTest {
	@MockBean
	DietRepository dietRepo;

	@MockBean
	FoodRepository foodRepo;

	@Autowired
	DietService dietService;

	@Autowired
	NutritionGuruService nutrionGuruService;

	@Test
	public void testGetDietPlan() {
		PersonData personDetails = new PersonData(170, 69);
		Diet diet = new Diet(1, "weight loss", null);
		when(dietRepo.findByDietType("weight loss")).thenReturn(diet);
		Diet dietDetails = dietRepo.findByDietType("weight loss");
		assertEquals(diet, dietDetails);

	}

	@Test
	public void testSaveDietPlan() {
		Diet diet = new Diet(1, "weight loss", null);
		when(dietRepo.save(diet)).thenReturn(diet);
		Diet dietDetails = dietService.saveDietPlan(diet);
		assertEquals(diet.getDietId(), dietDetails.getDietId());
	}

	@Test
	public void testSaveFoodDetails() {
		Food food = new Food(1, "lunch", "2", "soup", null);
		when(foodRepo.save(food)).thenReturn(food);
		Food foodDetails = dietService.saveFoodDetails(food);
		assertEquals(food.getFoodId(), foodDetails.getFoodId());
	}

	@Test
	public void testGetDietDetails() {
		List<Diet> diet = new ArrayList<>();
		diet.add(new Diet(1, "weightloss", null));
		diet.add(new Diet(2, "weight gain", null));
		when(dietRepo.findAll()).thenReturn(diet);
		List<Diet> dietDetails = dietService.getDietDetails();
		assertEquals(dietDetails, diet);
	}

	@Test
	public void testGetFoodDetails() {
		List<Food> food = new ArrayList<>();
		food.add(new Food(1, "breafast", "8", "protein drink", null));
		food.add(new Food(2, "breafast", "8", "protein drink", null));
		when(foodRepo.findAll()).thenReturn(food);
		List<Food> foodDetails = dietService.getFoodDetails();
		assertEquals(food, foodDetails);
	}

}
