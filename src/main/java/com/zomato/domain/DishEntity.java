package com.zomato.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "zomato_tab")
public class DishEntity {
	@Id
	//@GenericGenerator(name="idGen", strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cuisine_id")
	private int cuisineId;
	@Column(name = "cuisine_name")
	private String cuisineName;
	@Column(name = "cuisine_type")
	private String cuisineType;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name= "cuisine_ingredients")
	private String ingredients;
	@Column(name= "cooking_method")
	private String cookingInstructions;

	public int getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
	}

	public String getCuisine_name() {
		return cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getCookingInstructions() {
		return cookingInstructions;
	}

	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}

	public String getCuisineName() {
		return cuisineName;
	}
	
	
	

}
