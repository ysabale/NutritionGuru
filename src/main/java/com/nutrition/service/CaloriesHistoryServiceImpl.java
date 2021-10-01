package com.nutrition.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;
import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;
import com.nutrition.exception.DataNotFoundException;
import com.nutrition.exception.InvalidInputException;
import com.nutrition.repository.WeightGainRepository;
import com.nutrition.repository.WeightLossRepository;

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
