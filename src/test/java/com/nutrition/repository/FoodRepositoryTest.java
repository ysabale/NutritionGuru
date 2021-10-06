package com.nutrition.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nutrition.model.Food;


@SpringBootTest
@AutoConfigureMockMvc
class FoodRepositoryTest {

	@MockBean
	FoodRepository foodRepo;
	
	@Test
	void testGetPlanDetails() {
			List<Food> food=new ArrayList<>();
			food.add(new Food(1,"breafast","8","protein drink",null));
			food.add(new Food(2,"breafast","8","protein drink",null));
			when(foodRepo.getPlanDetails(1)).thenReturn(food);
			List<Food> foodDetails=foodRepo.getPlanDetails(1);
		    assertEquals(food,foodDetails);
	}

}
