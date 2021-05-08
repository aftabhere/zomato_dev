package com.zomato.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.zomato.domain.DishEntity;
import com.zomato.repository.DishRepository;
import com.zomato.requestvo.DishDetailsVO;
import com.zomato.response.DishDescriptionResponseVO;
import com.zomato.response.DishRegistrationResponseVO;
import com.zomato.response.RegisteredDishesResponse;
import com.zomato.response.ResponseMessageVO;

@Service
public class DishServiceImpl implements DishService {
	private static final Logger logger = LoggerFactory.getLogger(DishServiceImpl.class);
	@Autowired
	DishRepository dishRepository;

	@Override
	public DishRegistrationResponseVO registerDish(DishDetailsVO details) {
		logger.info("DishServiceImpl registerDish method");
		DishEntity entity = null;
		DishEntity response = null;
		String ingredient = "";
		DishRegistrationResponseVO responseVO = null;
		if (details != null) {
			entity = new DishEntity();
			entity.setCuisineName(details.getDishName());
			entity.setCuisineId(details.getDishId());
			entity.setCuisineType(details.getDishType());
			entity.setCreateDate(new Date());
			entity.setCookingInstructions(details.getCookingInstrucion());
			ingredient = convertIngredients(details.getIngredients());
			entity.setIngredients(ingredient);

			try {
				response = dishRepository.save(entity);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			// prpeare response
			responseVO = new DishRegistrationResponseVO();
			responseVO.setCode("200");
			responseVO.setDishId(response.getCuisineId());
			responseVO.setDishName(response.getCuisine_name());
			responseVO.setDishType(response.getCuisineType());
			List<String> ingredientList = new ArrayList<String>(Arrays.asList(response.getIngredients().split(" , ")));
			responseVO.setIngredients(ingredientList);
			responseVO.setSuccess(true);
			responseVO.setMessage("Dish registered successfully with Zomato");
			List<String> detail = new ArrayList<>();
			detail.add(responseVO.getMessage());
			responseVO.setDetails(detail);

		}
		return responseVO;
	}

	private String convertIngredients(List<String> data) {
		String result = null;
		if (data != null) {
			result = data.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
		}
		return result;
	}

	@Override
	public ResponseMessageVO updateDish(DishDetailsVO updatedDetails) {
		logger.info("DishServiceImpl updateDish method");
		Optional<DishEntity> entity =Optional.empty();
		DishEntity response =null;
		ResponseMessageVO responseMessage=null;
		if(updatedDetails !=null){
			try{
			entity =dishRepository.findById(updatedDetails.getDishId());
			if(entity.isPresent()){
				response=entity.get();
				response.setCookingInstructions(updatedDetails.getCookingInstrucion());
				response.setIngredients(convertIngredients(updatedDetails.getIngredients()));
				try{
					dishRepository.save(response);
				}catch(DataAccessException ex){
					logger.error("Exception occures while updating the dish details :"+ex.getMessage());
				}
				responseMessage =new ResponseMessageVO();
				responseMessage.setCode("200");
				responseMessage.setSuccess(true);
				responseMessage.setMessage("Dish updated successfully");
				responseMessage.setDetails(Arrays.asList(responseMessage.getMessage()));
				return responseMessage;
			}
			}catch(Exception e){
				//log the exception
				logger.error("Exception occures while feching the dish details :"+e.getMessage());
			}
		}
		responseMessage =new ResponseMessageVO();
		responseMessage.setCode("400");
		responseMessage.setSuccess(false);
		responseMessage.setMessage("Dish details not found. ");
		responseMessage.setDetails(Arrays.asList(responseMessage.getMessage()));
		return responseMessage;
	}

	@Override
	public RegisteredDishesResponse getAllRegisteredDishes() {
		List<DishEntity> entityList=null;
		DishDescriptionResponseVO response=null;
		RegisteredDishesResponse finalResponse=null;
		List<DishDescriptionResponseVO> responseList =new ArrayList<>();
		entityList = (List<DishEntity>) dishRepository.findAll();
		if(entityList!=null){
			for(DishEntity entity: entityList){
				response =new DishDescriptionResponseVO();
				response.setDishId(entity.getCuisineId());
				response.setDishName(entity.getCuisine_name());
				response.setDishType(entity.getCuisineType());
				response.setIngredients(new ArrayList<String>(Arrays.asList(entity.getIngredients().split(" , "))));
				responseList.add(response);
			}
			finalResponse =new RegisteredDishesResponse();
			finalResponse.setCode("200");
			finalResponse.setSuccess(true);
			finalResponse.setMessage("Rigestered dishes fetched successfully ");
			finalResponse.setDetails(Arrays.asList(finalResponse.getMessage()));
			finalResponse.setRegisteredDishes(responseList);
			return finalResponse;
			
		}
		finalResponse =new RegisteredDishesResponse();
		finalResponse.setCode("404");
		finalResponse.setSuccess(false);
		finalResponse.setMessage("Dishes not found. ");
		finalResponse.setDetails(Arrays.asList(finalResponse.getMessage()));
		finalResponse.setRegisteredDishes(responseList);
		
		return finalResponse;
	}

	@Override
	public ResponseMessageVO deleteDishById(Integer dishId) {
		int flag=0;
		ResponseMessageVO response= null;
		   if(dishId!=null){
			   try{
			   dishRepository.deleteById(dishId);
			   flag++;
			   if(flag>0){
				   response =new ResponseMessageVO();
				   response.setCode("200");
				   response.setSuccess(true);
				   response.setMessage("Dishes deleted successfully. ");
				   response.setDetails(Arrays.asList(response.getMessage()));
				   return response;
			   }
			   }catch(Exception e){
				   logger.error("Exception occures while deleting the dish details :"+e.getMessage()); 
			   }
		   }
		   response =new ResponseMessageVO();
		   response.setCode("404");
		   response.setSuccess(false);
		   response.setMessage("Sorry!! Dishes doesn't exist. ");
		   response.setDetails(Arrays.asList(response.getMessage()));
		   return response;
	}
	
	


}
