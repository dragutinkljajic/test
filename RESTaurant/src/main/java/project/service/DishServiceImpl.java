package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Dish;
import project.repository.DishRepository;

@Service
public class DishServiceImpl implements DishService{
	
	@Autowired
	private DishRepository dishRepository;
	
	@Override
	public Dish addDish(Dish dish) {
		return this.dishRepository.save(dish);
	}

	@Override
	public List<Dish> getDishesByMenuId(Long id) {
		return this.dishRepository.findDishesByMenuId(id);
	}

	@Override
	public Dish getDishByLabel(String label) {
		return this.dishRepository.findDishByLabel(label);
	}

	@Override
	public void deleteDishById(Long id) {
		this.dishRepository.removeDishById(id);
	}

	@Override
	public Dish getDishById(Long id) {
		return dishRepository.findDishById(id);
	}
	
	
}
