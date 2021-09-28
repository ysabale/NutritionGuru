package com.nutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutrition.config.NutritionCustomException;
import com.nutrition.model.PersonData;
import com.nutrition.service.NutritionGuruService;


/**
 * @author aymomin
 *
 */
@RestController
@RequestMapping(path = "api")
@CrossOrigin
public class NutritionGuruController {
	
	@Autowired
	NutritionGuruService nutritionGuruService;
	
	/**
	 * @param personDetail
	 * @return Diet Type
	 * @throws NutritionGuruException
	 */
	@PostMapping("/person/BMI")
	public ResponseEntity<String> getPersonDietType( @RequestBody PersonData personDetail) throws NutritionCustomException {
		String dietType = nutritionGuruService.getPersondietType(personDetail);
		return new ResponseEntity<String>(dietType, HttpStatus.OK);
	}
	
}
