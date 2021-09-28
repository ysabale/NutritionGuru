package com.nutrition.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nutrition.model.PersonData;

@Service
public class NutritionGuruService {

	public String getPersondietType(PersonData personDetail) {

		Logger logger = LoggerFactory.getLogger(NutritionGuruService.class);
		String bodyType = null;
		float heightInMeter = (personDetail.getPersonHeight()) / 100;
		float weight = personDetail.getPersonWeight();
		float BMI = (weight / (heightInMeter * heightInMeter));
		logger.info("Body mass index:" + BMI);

		if (BMI < 18.5) {
			bodyType = "under Weight";
		} else if (BMI > 18.5 && BMI < 24.9) {
			bodyType = "Healthy Weight";
		} else {
			bodyType = "Over Weight";
		}

		if (bodyType.equalsIgnoreCase("Over Weight")) {
			return "Weight Loss";
		} else if (bodyType.equalsIgnoreCase("under Weight")) {
			return "Weight Gain";
		} else {
			return "Stable Weight";
		}
	}

}
