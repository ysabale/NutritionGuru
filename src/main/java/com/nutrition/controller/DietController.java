package com.nutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutrition.config.NutritionCustomException;
import com.nutrition.model.Diet;
import com.nutrition.model.PersonData;
import com.nutrition.service.DietService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DietController {

	@Autowired
	private DietService dietservice;
	
	@PostMapping("/getDietPlan")
	public ResponseEntity<Diet> getDietPlan( @RequestBody PersonData personDetail) throws NutritionCustomException {
		return new ResponseEntity<Diet>(dietservice.getDietPlan(personDetail), HttpStatus.OK);
	}
	
	
	@PostMapping("/saveDietPlan")
	public ResponseEntity<String> saveDietPlan( @RequestBody Diet diet) throws NutritionCustomException {
		dietservice.saveDietPlan(diet);
		return new ResponseEntity<String>("Save Diet Plan Successfully", HttpStatus.OK);
		
	}
}