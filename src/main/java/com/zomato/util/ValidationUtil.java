package com.zomato.util;

import com.zomato.exception.DishDetailsUpdationException;
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
				throw new DishDetailsUpdationException("missing cookinginstruction/ingredients for dish updation");
				
			}
		 }
}
