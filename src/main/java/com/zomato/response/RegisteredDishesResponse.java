package com.zomato.response;

import java.util.List;

public class RegisteredDishesResponse extends ResponseMessageVO {
   List<DishDescriptionResponseVO> registeredDishes;

public List<DishDescriptionResponseVO> getRegisteredDishes() {
	return registeredDishes;
}

public void setRegisteredDishes(List<DishDescriptionResponseVO> registeredDishes) {
	this.registeredDishes = registeredDishes;
}
    
}
