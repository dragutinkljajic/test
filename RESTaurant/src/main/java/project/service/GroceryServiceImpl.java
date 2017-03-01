package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Grocery;
import project.repository.GroceryRepository;

@Service
public class GroceryServiceImpl implements GroceryService{
	
	@Autowired
	private GroceryRepository groceryRepository;

	@Override
	public Grocery addGrocery(Grocery grocery) {
		return groceryRepository.save(grocery);
	}

	@Override
	public Grocery getGroceryByLabel(String label) {
		return groceryRepository.findGroceryByLabel(label);
	}

	@Override
	public void deleteGroceryById(Long id) {
		groceryRepository.removeGroceryById(id);
	}

	@Override
	public List<Grocery> getAllGroceries() {
		return groceryRepository.findAllGroceries();
	}
}
