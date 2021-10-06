package com.nutrition.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;
import com.nutrition.model.WeightGainResponse;
import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;
import com.nutrition.model.WeightLossResponse;
import com.nutrition.service.CaloriesHistoryService;

<<<<<<< HEAD

=======
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
@RestController
public class CaloriesHistoryController {
	static Logger logger = Logger.getLogger(CaloriesHistoryController.class.getName());
	@Autowired
	private CaloriesHistoryService caloriesHistory;

	
	/*
<<<<<<< HEAD
=======
	 * 
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
	 * This method is used for fetch calories history of weight loss
	 */
	@PostMapping("/getWeightLossHistory")
	public ResponseEntity<WeightLossResponse> getCaloriesHistoryForWeightLoss(
			@RequestBody WeightLossRequest weightLossRequest) {
<<<<<<< HEAD
=======
		System.out.println("weightLossRequest=******==="+weightLossRequest.getFromDate());
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
		List<WeightLoss> weightLossList = caloriesHistory.getCaloriesHistoryForWeightLoss(weightLossRequest);
		WeightLossResponse weightLossResponse = new WeightLossResponse();
		weightLossResponse.setList(weightLossList);
		weightLossResponse.setUserName(weightLossRequest.getUserName());
		if (weightLossList.isEmpty()) {
			logger.error("Data not found getWeightLossHistory controller class");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(weightLossResponse, HttpStatus.OK);
	}

	/*
	 * This method is used for fetch calories history of weight gain
	 */
	@PostMapping("/getWeightGainHistory")
	public ResponseEntity<WeightGainResponse> getCaloriesHistoryForWeightGain(
			@RequestBody WeightGainRequest weightGainRequest) {
		List<WeightGain> weightGainList = caloriesHistory.getCaloriesHistoryForWeightGain(weightGainRequest);
		WeightGainResponse weightGainResponse = new WeightGainResponse();
		weightGainResponse.setList(weightGainList);
		weightGainResponse.setUserName(weightGainRequest.getUserName());
		if (weightGainList.isEmpty()) {
			logger.error("Data not found getWeightGainHistory controller class");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(weightGainResponse, HttpStatus.OK);
	}
}
