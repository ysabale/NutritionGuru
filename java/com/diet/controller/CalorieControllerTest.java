package com.diet.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import com.diet.model.Users;
import com.diet.service.CalorieService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CalorieControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	
	@Autowired
	CalorieService calorieService;
	
	ObjectMapper Obj = new ObjectMapper();
	
	@Test
	public void getCalorieDetails() throws Exception
	{
		
		  Users user = new Users(); 
		  user.setAge(31); 
		  user.setGender("F");
		  user.setExcercise(1);
		  user.setHeight(150.3f); 
		  user.setWeight(75.00f); String
		  
		  
		  jsonAsString = Obj.writeValueAsString(user); mockMvc.perform(
		  post("/calorieDetails").header("Origin",
		  "*").contentType(MediaType.APPLICATION_JSON_VALUE)
		  .content(jsonAsString).accept(MediaType.APPLICATION_JSON_VALUE))
		  .andExpect(status().isOk());
		 
	}
	
	
}
