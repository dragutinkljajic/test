package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Restaurant;
import project.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public Restaurant getRestaurantById(Long id) {
		Assert.notNull(id, "id cannot be null.");
		return this.restaurantRepository.findRestaurantById(id);
	}

	@Override
	public Restaurant addRestaurant(Restaurant r) {
		
		return this.restaurantRepository.save(r);
	}

	@Override
	public List<Restaurant> getAll() {
		return this.restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurantByName(String name) {
		Assert.notNull(name, "name cannot be null.");
		return this.restaurantRepository.findRestaurantByName(name);
	}

	@Override
	public void updateRestaurantName(Restaurant r) {
		Assert.notNull(r, "Cannot be null");
		restaurantRepository.updateName(r.getRestaurantID(), r.getName());
	}

	@Override
	public void updateRestaurantDetails(Restaurant r) {
		Assert.notNull(r, "Cannot be null");
		restaurantRepository.updateDetails(r.getRestaurantID(), r.getType(), r.getDescription());
	}
}
