package com.nutrition.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nutrition.model.PersonData;


/**
 * @author aymomin
 * @implNote 
 * 
 *
 */
@Service
public class NutritionGuruService {

	
	Logger logger = LoggerFactory.getLogger(NutritionGuruService.class);
	private static final String weightGain = "Weight Gain";
	private static final String stableWeight = "Stable Weight";
	private static final String weightLoss = "Weight Loss";

	/**
	 * @param personDetail
	 * Convert person height centemeter to meter
	 * calculate body mass index =weight / (heightInMeter * heightInMeter)
	 * @return String
	 * 
	 */
	public String getPersonDietType(PersonData personDetail) {
		float heightInMeter = (personDetail.getPersonHeight()) / 100;
		float weight = personDetail.getPersonWeight();
		float bMI = (weight / (heightInMeter * heightInMeter));
		logger.info("Body mass index:" + bMI);

		if (bMI < 18.5) {
			return weightGain;
		} else if (bMI > 18.5 && bMI < 24.9) {
			return stableWeight;
		} else {
			return weightLoss;
		}

	}

}
