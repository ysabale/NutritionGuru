package com.nutrition.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutrition.model.WeightGain;

<<<<<<< HEAD

=======
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
public interface WeightGainRepository extends JpaRepository<WeightGain, Long>{
	List<WeightGain> findAllByCaloriesIntakeDateBetweenAndUserName(Date caloriesIntakeDate,Date toDate,String userName);
}