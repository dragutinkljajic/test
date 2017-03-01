package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Drink;
import project.repository.DrinkRepository;

@Service
public class DrinkServiceImpl implements DrinkService{
	
	@Autowired
	private DrinkRepository drinkRepository;
	
	@Override
	public Drink addDrink(Drink drink) {
		return drinkRepository.save(drink);
	}

	@Override
	public List<Drink> getDrinksByDrinksMenuId(Long id) {
		return drinkRepository.findDrinksByDrinksMenuId(id);
	}

	@Override
	public Drink getDrinkByLabel(String label) {
		return drinkRepository.findDrinkByLabel(label);
	}

	@Override
	public void deleteDrinkById(Long id) {
		drinkRepository.removeDrinkById(id);
	}

	@Override
	public List<Drink> getAllDrinks() {
		return drinkRepository.findAllDrinks();
	}

	@Override
	public Drink getDrinkById(Long id) {
		return drinkRepository.findDrinkById(id);
	}

}
