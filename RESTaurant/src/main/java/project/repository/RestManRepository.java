package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.RestaurantManager;

public interface RestManRepository extends Repository<RestaurantManager, Long>{
	
	@Query("select rm from RestaurantManager rm where rm.userID = ?1")
	public RestaurantManager findRestaurantManagerById(Long id);
	
	public List<RestaurantManager> findAll();
	
	public RestaurantManager save(RestaurantManager rm);
}
