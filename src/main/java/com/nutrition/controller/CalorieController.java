package com.nutrition.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutrition.model.UserCalorieDetails;
import com.nutrition.service.CalorieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CalorieController {

	@Autowired
	private CalorieService calorieService;

	@PostMapping(value = "/calorieDetails",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Float> getCalorieDetails(@RequestBody @Valid UserCalorieDetails users) {
		log.info("Hello in Nutrition Portal");
		float usersCalorie = calorieService.calculateBMR(users);
		log.info("Total Calorie:"+usersCalorie);
		return new ResponseEntity<Float>(usersCalorie, HttpStatus.OK);
	}

	@GetMapping("/userDetails")
	public UserCalorieDetails getUserDetails() {
		return new UserCalorieDetails();
	}

}
