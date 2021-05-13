package com.zomato.response;

import java.util.List;

public class DishRegistrationResponseVO extends ResponseMessageVO {
	private int dishId;
	private String dishName;
	private String dishType;
	private List<String> ingredients;
	private String cookingInstructions;
	
	
	public String getCookingInstructions() {
		return cookingInstructions;
	}
	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public int getDishId() {
		return dishId;
	}
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDishType() {
		return dishType;
	}
	public void setDishType(String dishType) {
		this.dishType = dishType;
	}
	
	
	

}
