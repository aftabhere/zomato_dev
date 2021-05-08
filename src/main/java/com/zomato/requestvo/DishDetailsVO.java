package com.zomato.requestvo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DishDetailsVO implements Serializable{
	private static final long serialVersionUID = 1L;
	public int dishId;
	public String dishName;
	public String dishType;
	public List<String> ingredients;
	public String cookingInstrucion;
	
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
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public String getCookingInstrucion() {
		return cookingInstrucion;
	}
	public void setCookingInstrucion(String cookingInstrucion) {
		this.cookingInstrucion = cookingInstrucion;
	}
	
}
