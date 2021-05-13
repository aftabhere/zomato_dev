package com.zomato.util;

import com.zomato.exception.DishDetailsValidationException;
import com.zomato.exception.InvalidDetailsException;
import com.zomato.exception.InvalidDishRegistrationException;
import com.zomato.requestvo.DishDetailsVO;

public class ValidationUtil {
	
	public static void validateRegistrationDetails(DishDetailsVO details){
		if(details==null || details.getDishName()==null || details.getDishName().isEmpty()
				|| details.getDishType()==null || details.getDishType().isEmpty()
				|| details.getCookingInstrucion()==null || details.getCookingInstrucion().isEmpty()
				|| details.getIngredients()==null | details.getIngredients().isEmpty()){
			throw new InvalidDishRegistrationException("Please specify mandatory details for disg registration");
		}
	}
		
		 public static void validateDishUpdationDetails(DishDetailsVO detail){
			if(detail==null || detail.getCookingInstrucion()==null 
					         || detail.getCookingInstrucion().isEmpty()
					         || detail.getIngredients()==null  
					         || detail.getIngredients().isEmpty()){
				throw new DishDetailsValidationException("missing cookinginstruction/ingredients for dish updation");
				
			}
		 }
		 
		 public static void validateDeleteDishById(Integer id){
			 if(id==null || id==0){
				 throw new InvalidDetailsException("Invalid details found..");
			 }
		 }
}
