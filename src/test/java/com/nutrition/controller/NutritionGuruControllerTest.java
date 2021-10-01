package com.nutrition.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrition.authentication.JwtTokenUtil;
import com.nutrition.model.PersonData;
import com.nutrition.service.LoginServiceImpl;
import com.nutrition.service.NutritionGuruService;

/**
 * @author AYMOMIN
 *
 */
@AutoConfigureMockMvc
@SpringBootTest
class NutritionGuruControllerTest {

	@MockBean
	private NutritionGuruService nutritionGuruService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	NutritionGuruController nutritionGuruController;

	@MockBean
	LoginServiceImpl loginService;

	private PersonData person;

	/**
	 * @apiNote test case cover diet type is stable weight
	 * @throws Exception
	 */
	@DisplayName("post /api/person/dietType")
	@Test
	void testGetPersonDietType() throws Exception {
		when(nutritionGuruService.getPersonDietType(any())).thenReturn("Stable Weight");
		PersonData personDetails = new PersonData(120, 130);
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
				.loadUserByUsername("Test@gmail.com");
		System.out.println("Nutrition Service" + nutritionGuruService.getPersonDietType(person));
		mockMvc.perform(post("/api/person/dietType")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(personDetails)))
				.andExpect(status().isOk());
	}

	/**
	 * @apiNote test case cover diet type is stable weight
	 * @throws Exception
	 */
	@Test
	void testGetPersonDietTypeInvalid() throws Exception {
		when(nutritionGuruService.getPersonDietType(any())).thenReturn("Stable Weight");
		doReturn(new User("Test@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
				.loadUserByUsername("Test@gmail.com");
		PersonData personDetails = new PersonData(0, 130);
		System.out.println("Nutrition Service" + nutritionGuruService.getPersonDietType(person));
		mockMvc.perform(post("/api/person/dietType").header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(personDetails)))
				.andExpect(status().is(400));

	}

	public static String getToken() {
		UserDetails userDetails = new User("Test@gmail.com", "xyz12345", new ArrayList<>());
		String jwt = new JwtTokenUtil().generateToken(userDetails);
		return jwt;
	}

	static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
