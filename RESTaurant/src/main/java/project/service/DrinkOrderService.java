package project.service;

import java.util.List;

import project.domain.DrinkOrder;

public interface DrinkOrderService {

	DrinkOrder save(DrinkOrder drinkOrder);
	
	DrinkOrder getDrinkOrderById(Long id);
	
	List<DrinkOrder> getAll();
}
