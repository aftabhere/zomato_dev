package com.zomato.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zomato.requestvo.DishDetailsVO;
import com.zomato.response.DishRegistrationResponseVO;
import com.zomato.response.RegisteredDishesResponse;
import com.zomato.response.ResponseMessageVO;
import com.zomato.service.DishService;

@RestController
public class DishController {
	private static final Logger logger = LoggerFactory.getLogger(DishController.class);
	@Autowired
	DishService dishService;
	
	@PostMapping(value="/registerDish")
	public ResponseEntity<DishRegistrationResponseVO> registerDish(@RequestBody DishDetailsVO details){
		logger.info("DishController regiserDish method ");
		//todo : validate each field of DishDetailsVO if fail throw exception
		DishRegistrationResponseVO response=null;
		 if(details!=null){
			response= dishService.registerDish(details);
		 }		 
		 return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateDish")
	public ResponseEntity<ResponseMessageVO> updateDish(@RequestBody DishDetailsVO updatedDetails){
		ResponseMessageVO response=null;
		logger.info("DishController updateDish method ");
	 //todo : validate each field of DishDetailsVO if fail throw exception
		if(updatedDetails!=null){
		response = dishService.updateDish(updatedDetails);
		}else{
			//throw the exception
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping(value="/allRegisteredDishes")
	public ResponseEntity<RegisteredDishesResponse> getAllRegisteredDishes(){
		logger.info("DishController getAllRegisteredDishes method ");
		return new ResponseEntity<>(dishService.getAllRegisteredDishes(),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteDishById")
	public ResponseEntity<ResponseMessageVO> deleteDishById(@RequestParam Integer dishId){
		logger.info("DishController deleteDishById method ");
		//to do : validate request if null throw exception
		return new ResponseEntity<>(dishService.deleteDishById(dishId),HttpStatus.OK);
	}

}
