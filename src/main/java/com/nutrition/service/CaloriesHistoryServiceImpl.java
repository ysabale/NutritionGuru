package com.nutrition.service;

<<<<<<< HEAD
=======
import java.text.SimpleDateFormat;
import java.util.Date;
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

import com.nutrition.config.DataNotFoundException;
=======
import org.springframework.util.FileSystemUtils;

>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;
import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;
<<<<<<< HEAD
import com.nutrition.repository.WeightGainRepository;
import com.nutrition.repository.WeightLossRepository;


=======
import com.nutrition.exception.DataNotFoundException;
import com.nutrition.exception.InvalidInputException;
import com.nutrition.repository.WeightGainRepository;
import com.nutrition.repository.WeightLossRepository;

>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
@Service
public class CaloriesHistoryServiceImpl implements CaloriesHistoryService {
	static Logger logger = Logger.getLogger(CaloriesHistoryServiceImpl.class.getName());
	@Autowired
	private WeightLossRepository weightLossRepository;
	@Autowired
	private WeightGainRepository weightGainRepository;

	@Override
	public List<WeightLoss> getCaloriesHistoryForWeightLoss(WeightLossRequest weightLossRequest) {
		List<WeightLoss> foodIntakeCategory = null;
		try {
			WeightLoss weightLoss = new WeightLoss();
			weightLoss.setCaloriesIntakeDate(weightLossRequest.getFromDate());
			weightLoss.setUserName(weightLossRequest.getUserName());
			foodIntakeCategory = weightLossRepository.findAllByCaloriesIntakeDateBetweenAndUserName(
					weightLoss.getCaloriesIntakeDate(),weightLossRequest.getToDate(), weightLoss.getUserName());
			if (!foodIntakeCategory.isEmpty()) {
				return foodIntakeCategory;
			} else {
				logger.error("Weight loss history data not found!");
				throw new DataNotFoundException();
			}
		} catch (Exception ex) {
			logger.error("There is some problem in getCaloriesHistoryForWeightLoss service class");
			throw new DataNotFoundException();
		}
	}

	@Override
	public List<WeightGain> getCaloriesHistoryForWeightGain(WeightGainRequest weightGainRequest) {
		List<WeightGain> foodIntakeCategory = null;
		try {
			WeightGain weightGain = new WeightGain();
			weightGain.setCaloriesIntakeDate(weightGainRequest.getFromDate());
			weightGain.setUserName(weightGainRequest.getUserName());
			foodIntakeCategory = weightGainRepository.findAllByCaloriesIntakeDateBetweenAndUserName(
					weightGain.getCaloriesIntakeDate(),weightGainRequest.getToDate(), weightGain.getUserName());
			if (!foodIntakeCategory.isEmpty()) {
				return foodIntakeCategory;
			} else {
				logger.error("Weight gain history not found");
				throw new DataNotFoundException();
			}
		} catch (Exception e) {
			logger.error("There is some problem in getCaloriesHistoryForWeightGain service class");
			throw new DataNotFoundException();
		}
	}

}
