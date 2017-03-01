package project.service;

import java.util.List;

import project.domain.Grocery;

public interface GroceryService {
	Grocery addGrocery(Grocery grocery);
	
	Grocery getGroceryByLabel(String label);
	
	void deleteGroceryById(Long id);
	
	List<Grocery> getAllGroceries();
}
