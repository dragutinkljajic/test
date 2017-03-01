package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Restaurant;

public interface RestaurantRepository extends Repository<Restaurant, Long>{
	
	@Query("select r from Restaurant r where r.restaurantID = ?1")
	public Restaurant findRestaurantById(Long id);
	
	@Query("select r from Restaurant r where r.name = ?1")
	public Restaurant findRestaurantByName(String name);
	
	public List<Restaurant> findAll();
	
	public Restaurant save(Restaurant r);
	
	@Modifying
	@Query("update Restaurant r set r.name = ?2 where r.restaurantID = ?1 ")
	public void updateName(Long id, String name);
	
	@Modifying
	@Query("update Restaurant r set r.type = ?2, r.description = ?3 where r.restaurantID = ?1")
	public void updateDetails(Long id, String type, String description);
}
