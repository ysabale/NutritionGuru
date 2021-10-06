package com.nutrition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nutrition.exception.NutritionCustomException;
import com.nutrition.model.Diet;
import com.nutrition.model.Food;
import com.nutrition.model.PersonData;
import com.nutrition.service.DietService;

@RestController
public class DietController {

	@Autowired
	private DietService dietservice;

	@PostMapping("/dietPlanDetails")
	public ResponseEntity<List<Food>> dietPlanDetails(@RequestBody PersonData personDetail)
			throws NutritionCustomException {
		return new ResponseEntity<>(dietservice.getDietPlan(personDetail), HttpStatus.OK);
	}

	@PostMapping("/saveDietType")
	public ResponseEntity<Diet> saveDietPlan(@RequestBody Diet diet) throws NutritionCustomException {
		Diet dietdetails = dietservice.saveDietPlan(diet);
		return new ResponseEntity<>(dietdetails, HttpStatus.OK);

	}

	@PostMapping("/saveFoodDetails")
	public ResponseEntity<Food> saveFoodDetails(@RequestBody Food food) throws NutritionCustomException {
		Food foodDetails = dietservice.saveFoodDetails(food);
		return new ResponseEntity<>(foodDetails, HttpStatus.OK);

	}

	@GetMapping("/getDietDetails")
	public ResponseEntity<List<Diet>> getDietDetails() throws NutritionCustomException {
		return new ResponseEntity<>(dietservice.getDietDetails(), HttpStatus.OK);

	}

	@GetMapping("/getFoodDetails")
	public ResponseEntity<List<Food>> getFoodDetails() throws NutritionCustomException {
		return new ResponseEntity<>(dietservice.getFoodDetails(), HttpStatus.OK);

	}

}