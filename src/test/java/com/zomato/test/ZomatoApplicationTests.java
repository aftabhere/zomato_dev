/*package com.zomato.test;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zomato.controller.DishController;
import com.zomato.requestvo.DishDetailsVO;
import com.zomato.response.DishRegistrationResponseVO;
import com.zomato.service.DishServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZomatoApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZomatoApplicationTests.class);
	
	@LocalServerPort
	private int port = 9080;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper = null;
	
	@Mock
	DishServiceImpl serviceImpl;
	
	
	@InjectMocks
	DishController dishCotroller;

		
	@BeforeEach
	public void setup() throws Exception {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(dishCotroller).build();
	}
	
	@Test
	public void registerDishTest() throws Exception{
		LOGGER.info(" *** registerDishTest method *** ");
		DishRegistrationResponseVO responseMessageVo = null;
		String expectedResponse = null;
		responseMessageVo = new DishRegistrationResponseVO();
		responseMessageVo.setMessage("Dish registered successfully with Zomato");
		responseMessageVo.setSuccess(true);
		responseMessageVo.setCode("200");
		responseMessageVo.setDetails(Arrays.asList("Dish registered successfully with Zomato"));
		responseMessageVo.setDishId(500);
		responseMessageVo.setDishName("Karahi Paneer");
		responseMessageVo.setDishType("VEG");
		expectedResponse = objectMapper.writeValueAsString(responseMessageVo);
		
		DishDetailsVO details =new DishDetailsVO();
		details.setDishId(500);
		details.setDishName("Karahi Paneer");
		details.setDishType("VEG");
		details.setIngredients(Arrays.asList("Paneer","Butter","Milk"));
		details.setCookingInstrucion("Western");
		
		String body =objectMapper.writeValueAsString(details);
		
		System.out.println("expected Repsponse "+expectedResponse);
		
		Mockito.when(serviceImpl.registerDish(details)).thenReturn(responseMessageVo);
				
		MvcResult response =  mockMvc.perform(MockMvcRequestBuilders.post("/registerDish").contentType(MediaType.APPLICATION_JSON).content(body)
				 .accept(MediaType.APPLICATION_JSON))
		         .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		         //.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Dish registered successfully with Zomato"))).andReturn();
	     System.out.println("Response :"+response.getResponse().getContentAsString());
		 System.out.println("Mvc Response :" + response.getResponse().getContentAsString());
		 JSONAssert.assertEquals(expectedResponse, response.getResponse().getContentAsString(), false);
			


	}

}
*/