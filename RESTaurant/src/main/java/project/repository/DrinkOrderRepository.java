package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.DrinkOrder;

public interface DrinkOrderRepository extends Repository<DrinkOrder, Long> {

	DrinkOrder save(DrinkOrder drinkOrder);
	
	@Query("select d from DrinkOrder d where d.id = ?1")
	DrinkOrder findDrinkOrderById(Long id);
	
	@Query("select d from DrinkOrder d")
	List<DrinkOrder> findAll();
}
