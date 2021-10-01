package com.nutrition.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nutrition.model.Diet;

@SpringBootTest
@AutoConfigureMockMvc
class DietRepositoryTest {

	@MockBean
	DietRepository dietRepo;
	
	@Test
	void testFindDietByDietId() {
		Diet diet = new Diet(1,"weight loss",null);
		when(dietRepo.findDietByDietId(1)).thenReturn(diet);
	    Diet dietDetails=dietRepo.findDietByDietId(1);
		assertEquals(diet.getDietId(),dietDetails.getDietId());
	}

	@Test
	void testFindByDietType() {
		Diet diet = new Diet(1,"weight loss",null);
		when(dietRepo.findByDietType("weight loss")).thenReturn(diet);
	    Diet dietDetails=dietRepo.findByDietType("weight loss");
		assertEquals(diet.getDietType(),dietDetails.getDietType());
	}

}
