package project.service;

import java.util.List;

import project.domain.Drink;

public interface DrinkService {
	Drink addDrink(Drink drink);
	
	List<Drink> getDrinksByDrinksMenuId(Long id);
	
	Drink getDrinkByLabel(String label);
	
	void deleteDrinkById(Long id);
	
	List<Drink> getAllDrinks();
	
	Drink getDrinkById(Long id);
}
