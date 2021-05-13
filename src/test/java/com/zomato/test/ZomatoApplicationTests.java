package com.zomato.test;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zomato.controller.DishController;
import com.zomato.requestvo.DishDetailsVO;
import com.zomato.response.DishDescriptionResponseVO;
import com.zomato.response.DishRegistrationResponseVO;
import com.zomato.response.RegisteredDishesResponse;
import com.zomato.response.ResponseMessageVO;
import com.zomato.service.DishServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =WebEnvironment.RANDOM_PORT)
/*@TestPropertySource(
		  locations = "classpath:spring-integration.properties")*/
public class ZomatoApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZomatoApplicationTests.class);
	
	@LocalServerPort
	private int port = 9080;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper = null;
	
	/*@Mock
	DishRepository repository;
	*/
	
	@Mock
	DishServiceImpl serviceImpl;
	
	@Autowired
    ApplicationContext context;
		
	@InjectMocks
	DishController dishCotroller;

		
	@BeforeEach
	public void setup() throws Exception {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(dishCotroller).build();
	}
	
	/*@Test
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
		
		when(serviceImpl.registerDish(details)).thenReturn(responseMessageVo);
		MvcResult response=null;
		try{
				
		 response =  mockMvc.perform(MockMvcRequestBuilders.post("/registerDish").contentType(MediaType.APPLICATION_JSON)
			     .content(body)
				 .accept(MediaType.APPLICATION_JSON))
		         .andExpect(status().is2xxSuccessful())
			     .andDo(MockMvcResultHandlers.print())
		         .andReturn();
	    
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		MockHttpServletResponse resp = response.getResponse();
		System.out.println("status :"+resp.getContentAsString());
		//System.out.println("Response :"+response.getResponse().getContentAsString());
		 System.out.println("Mvc Response :" + response.getResponse().getContentAsString());
		 JSONAssert.assertEquals("200", objectMapper.writeValueAsString(resp.getStatus()), false);
		
		//verify(repository,atLeastOnce());
	}
	*/
	
	
	
	@Test
	public void getDishByIdTest() throws Exception{
		LOGGER.info(" *** getDishByIdTest method *** ");
		DishRegistrationResponseVO response= null;
		MvcResult result=null;
		response =new DishRegistrationResponseVO();
		response.setSuccess(true);
		response.setCode("200");
		response.setDishId(12);
		response.setDishType("VEG");
		response.setDishName("Paneer Butter Masala");
		response.setMessage("Dish details fetched successfully..");
		response.setDetails(Arrays.asList(response.getMessage()));
		response.setIngredients(new ArrayList<String>(Arrays.asList("Milk", "Paneer","Butter")));
		String expectedResponse =objectMapper.writeValueAsString(response);
		
		when(serviceImpl.getDishById(12)).thenReturn(response);
					
		 result =  mockMvc.perform(MockMvcRequestBuilders.get("/getDishByID/12").contentType(MediaType.APPLICATION_JSON)
			     .accept(MediaType.APPLICATION_JSON))
		         .andExpect(status().is2xxSuccessful())
		         .andExpect(jsonPath("$.message", Matchers.is("Dish details fetched successfully..")))
				 .andExpect(jsonPath("$.success", Matchers.is(true))).andExpect(jsonPath("$.code", Matchers.is("200")))
				 .andExpect(jsonPath("$.dishType",Matchers.is("VEG")))
				 .andExpect(jsonPath("$.dishName",Matchers.is("Paneer Butter Masala")))
				 .andDo(MockMvcResultHandlers.print())
		         .andReturn();
	    
		System.out.println( "Response :"+result.getResponse().getContentAsString());
		
		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void deleteDishByIdTest() throws Exception{
		LOGGER.info(" *** deleteDishByIdTest method *** ");
		ResponseMessageVO response =null;
		String expectedResponse=null;
		MvcResult result=null;
		response =new ResponseMessageVO();
		   response.setCode("200");
		   response.setSuccess(true);
		   response.setMessage("Dishes deleted successfully.");
		   response.setDetails(Arrays.asList(response.getMessage()));
		   expectedResponse =objectMapper.writeValueAsString(response);
		   
		   System.out.println("Expected Response :"+expectedResponse);
		   
		   when(serviceImpl.deleteDishById(12)).thenReturn(response);
		   
		   result =  mockMvc.perform(MockMvcRequestBuilders.delete("/deleteDishById?dishId=12").contentType(MediaType.APPLICATION_JSON)
				     .accept(MediaType.APPLICATION_JSON))
			         .andExpect(status().is2xxSuccessful())
			         .andExpect(jsonPath("$.message", Matchers.is("Dishes deleted successfully.")))
					 .andExpect(jsonPath("$.success", Matchers.is(true))).andExpect(jsonPath("$.code", Matchers.is("200")))
				     .andDo(MockMvcResultHandlers.print())
			         .andReturn();
		   
		   System.out.println( "Response :"+result.getResponse().getContentAsString());
			
			JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getAllRegisteredDishTest() throws Exception{
		LOGGER.info(" *** getAllRegisteredDishTest method *** ");
		RegisteredDishesResponse response =null;
		List<DishDescriptionResponseVO> dishList=null;
		DishDescriptionResponseVO resp=null;
		String expectedResponse=null;
		MvcResult result=null;
		response=new RegisteredDishesResponse();
		response.setCode("200");
		response.setSuccess(true);
		response.setMessage("Rigestered dishes fetched successfully.");
		response.setDetails(Arrays.asList(response.getMessage()));
		dishList= new ArrayList<>();
		resp=new DishDescriptionResponseVO();
		resp.setDishId(6786);
		resp.setDishName("Paneer Tikka");
		resp.setDishType("VEG");
		resp.setIngredients(Arrays.asList("Paneer","Milk","Butter"));
		dishList.add(resp);
		response.setRegisteredDishes(dishList);
		expectedResponse =objectMapper.writeValueAsString(response);
		
		System.out.println("all redg dish :"+expectedResponse);
		
		when(serviceImpl.getAllRegisteredDishes()).thenReturn(response);
		
		result =  mockMvc.perform(MockMvcRequestBuilders.get("/allRegisteredDishes").contentType(MediaType.APPLICATION_JSON)
			     .accept(MediaType.APPLICATION_JSON))
		         .andExpect(status().is2xxSuccessful())
		         .andExpect(jsonPath("$.message", Matchers.is("Rigestered dishes fetched successfully.")))
				 .andExpect(jsonPath("$.success", Matchers.is(true))).andExpect(jsonPath("$.code", Matchers.is("200")))
				 .andExpect(jsonPath("$.registeredDishes[0].dishType",Matchers.is("VEG")))
				 .andExpect(jsonPath("$.registeredDishes[0].dishName",Matchers.is("Paneer Tikka")))
				 .andDo(MockMvcResultHandlers.print())
		         .andReturn();
		
		
        System.out.println( "All registered dish Response :"+result.getResponse().getContentAsString());
		
		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);		
	}
	
	@Test
	public void updateDishTest() throws Exception{
		LOGGER.info(" *** updateDishTest method *** ");
		ResponseMessageVO response=null;
		String expectedResponse=null;
		DishDetailsVO details=null;
		MvcResult result=null;
		response =new ResponseMessageVO();
		response.setCode("200");
		response.setSuccess(true);
		response.setMessage("Dish updated successfully");
		response.setDetails(Arrays.asList(response.getMessage()));
		expectedResponse =objectMapper.writeValueAsString(response);
		
		details=new DishDetailsVO();
		details.setDishId(8989);
		details.setDishName("Chiken Biriyani");
		details.setDishType("Non Veg");
		details.setIngredients(Arrays.asList("Chicken","Spice","Rice"));
		details.setCookingInstrucion("Hyderabadi traditional");
		
		String contentBody = objectMapper.writeValueAsString(details);
		
		
		when(serviceImpl.updateDish(details)).thenReturn(response);
				
		result =  mockMvc.perform(MockMvcRequestBuilders.put("/updateDish")
				 .contentType(MediaType.APPLICATION_JSON)
			     .characterEncoding("ISO-8859-1")
			     .content(contentBody)
			     .accept(MediaType.APPLICATION_JSON))
				 .andExpect(content().encoding("ISO-8859-1"))				 
		         .andExpect(status().is2xxSuccessful())
		         //.andExpect(jsonPath("$.message", Matchers.is("Dish updated successfully")))
				 //.andExpect(jsonPath("$.success", Matchers.is(true)))
			     //.andExpect(jsonPath("$.code", Matchers.is("200")))
				 .andDo(MockMvcResultHandlers.print())
		         .andReturn();
		System.out.println("jfdlkjfdl :"+result.getResponse().getContentAsString());
		
		//JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);			
	
	}

}
