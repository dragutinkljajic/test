package project.service;

import java.util.List;

import project.domain.RestaurantManager;
import project.domain.Supplier;

public interface RestManService {
	List<RestaurantManager> getAll();
	
	RestaurantManager addRestaurantManager(RestaurantManager rm);
	
	RestaurantManager getRestaurantManagerById(Long id);
}
