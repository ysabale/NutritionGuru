package com.nutrition.service;

<<<<<<< HEAD
=======
import java.util.Date;
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
import java.util.List;

import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;
import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;

<<<<<<< HEAD

=======
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
public interface CaloriesHistoryService {
	public List<WeightLoss> getCaloriesHistoryForWeightLoss(WeightLossRequest weightLossRequest);
	public List<WeightGain> getCaloriesHistoryForWeightGain(WeightGainRequest weightGainRequest);
}
