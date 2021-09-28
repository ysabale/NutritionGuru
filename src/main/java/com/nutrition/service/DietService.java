package com.nutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrition.model.Diet;
import com.nutrition.model.PersonData;
import com.nutrition.repository.DietRepository;

@Service
public class DietService {

	@Autowired
	private DietRepository dietRepository;
	
	@Autowired
	private NutritionGuruService nutritionGuruService;
	
	public Diet getDietPlan(PersonData personDetail) {
		String bodyType = nutritionGuruService.getPersondietType(personDetail);
		List<Diet> dietList= dietRepository.findAll();
		return dietList.get(0);
	}

	public void saveDietPlan(Diet diet) {
		dietRepository.save(diet);
	}

	
}