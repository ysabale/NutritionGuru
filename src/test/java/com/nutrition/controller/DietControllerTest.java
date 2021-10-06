package com.nutrition.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrition.authentication.JwtTokenUtil;
import com.nutrition.model.Diet;
import com.nutrition.model.Food;
import com.nutrition.model.PersonData;
import com.nutrition.service.DietService;
import com.nutrition.service.LoginServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class DietControllerTest {
	@MockBean
	DietService dietService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	LoginServiceImpl loginService;

	private Diet diet;

	private PersonData personDetails;

	@BeforeEach
	void initdietObject() {
		Food food1 = new Food(1, "lunch", "2", "soup", diet);
		Set<Food> food2 = new HashSet<>();
		food2.add(food1);
		diet = new Diet();
		diet.setDietId(1);
		diet.setDietType("weight loss");
		diet.setFoodList(food2);
		personDetails = new PersonData(170, 67);
	}

	@Test
	public void DietPlanDetails() throws Exception {

		List<Food> food = new ArrayList<>();
		food.add(new Food(1, "lunch", "2", "soup", diet));
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
				.loadUserByUsername("Test@gmail.com");
		when(dietService.getDietPlan(ArgumentMatchers.any())).thenReturn(food);
		String url = "/getDietDetails";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken());
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void SaveDietPlan() throws Exception {
		Mockito.when(dietService.saveDietPlan(ArgumentMatchers.any())).thenReturn(diet);
		String inputJson = mapToJson(diet);
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
		.loadUserByUsername("Test@gmail.com");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveDietType").content(inputJson)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken());
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);
	}

	@Test
	public void SaveFoodDetails() throws Exception {
		Food food = new Food(1, "lunch", "2", "soup", diet);
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
		.loadUserByUsername("Test@gmail.com");
		Mockito.when(dietService.saveFoodDetails(ArgumentMatchers.any())).thenReturn(food);
		String inputJson = mapToJson(food);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveFoodDetails").content(inputJson)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken());
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);

	}

	@Test
	public void GetDietDetails() throws Exception {
		List<Diet> diet1 = new ArrayList<>();
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
		.loadUserByUsername("Test@gmail.com");
		diet1.add(diet);
		when(dietService.getDietDetails()).thenReturn(diet1);
		String url = "/getDietDetails";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken());
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void GetFoodDetails() {
		List<Diet> diet1 = new ArrayList<>();
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
		.loadUserByUsername("Test@gmail.com");
		diet1.add(diet);
		when(dietService.getDietDetails()).thenReturn(diet1);
		String url = "/getFoodDetails";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
				.accept(MediaType.APPLICATION_JSON);

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	public static String getToken() {
		UserDetails userDetails = new User("Test@gmail.com", "xyz12345", new ArrayList<>());
		String jwt = new JwtTokenUtil().generateToken(userDetails);
		return jwt;
	}

}