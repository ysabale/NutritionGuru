package com.nutrition.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nutrition.model.WeightGain;
import com.nutrition.repository.WeightGainRepository;

@DataJpaTest
public class WeightGainRepositoryTest {

	@Autowired
	private WeightGainRepository weightGainRepository;
	private WeightGain weightGain;

	@BeforeEach
	void init() {
		weightGain = new WeightGain();
		// weightGain.set
	}
}
