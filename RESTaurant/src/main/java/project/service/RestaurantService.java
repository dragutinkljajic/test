package project.service;

import java.util.List;

import project.domain.Restaurant;

public interface RestaurantService {
	
	Restaurant getRestaurantById(Long id);
	
	Restaurant getRestaurantByName(String name);
	
	Restaurant addRestaurant(Restaurant r);
	
	List<Restaurant> getAll();
	
	void updateRestaurantName(Restaurant r);
	
	void updateRestaurantDetails(Restaurant r);
}
