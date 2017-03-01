package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.RestaurantManager;
import project.repository.RestManRepository;

@Service
public class RestManServiceImpl implements RestManService{
	
	@Autowired
	private RestManRepository restManRepository;
	
	@Override
	public List<RestaurantManager> getAll() {
		return this.restManRepository.findAll();
	}

	@Override
	public RestaurantManager addRestaurantManager(RestaurantManager rm) {
		return this.restManRepository.save(rm);
	}

	@Override
	public RestaurantManager getRestaurantManagerById(Long id) {
		return this.restManRepository.findRestaurantManagerById(id);
	}

}
