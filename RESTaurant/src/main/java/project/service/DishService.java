package project.service;

import java.util.List;

import project.domain.Dish;

public interface DishService {
	Dish addDish(Dish dish);
	
	List<Dish> getDishesByMenuId(Long id);
	
	Dish getDishByLabel(String label);
	
	void deleteDishById(Long id);
	
	Dish getDishById(Long id);
}
