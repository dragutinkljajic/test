package project.service;

import java.util.List;

import project.domain.DishOrder;

public interface DishOrderService {

	DishOrder save(DishOrder dishOrder);
	
	DishOrder getDishById(Long id);
	
	List<DishOrder> getAll();
}
