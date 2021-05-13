package com.zomato.service;

import com.zomato.requestvo.DishDetailsVO;
import com.zomato.response.DishRegistrationResponseVO;
import com.zomato.response.RegisteredDishesResponse;
import com.zomato.response.ResponseMessageVO;

public interface DishService {
	
	public DishRegistrationResponseVO registerDish(DishDetailsVO details);
	
	public ResponseMessageVO updateDish(DishDetailsVO updatedDetails);
	
	//public List<DishEntity> getAllRegisteredDishes();
	public RegisteredDishesResponse getAllRegisteredDishes();
	
	public ResponseMessageVO deleteDishById(Integer dishId);
	
	public DishRegistrationResponseVO getDishById(Integer dishId);

}
