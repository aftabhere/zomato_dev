package com.zomato.repository;

import org.springframework.data.repository.CrudRepository;

import com.zomato.domain.DishEntity;

public interface DishRepository extends CrudRepository<DishEntity,Integer>{
	
	

}
