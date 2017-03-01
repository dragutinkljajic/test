package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.DishOrder;

public interface DishOrderRepository extends Repository<DishOrder, Long> {

	public DishOrder save(DishOrder dishOrder);
	
	@Query("select d from DishOrder d")
	public List<DishOrder> findAll();
	
	@Query("select d from DishOrder d where d.id = ?1")
	public DishOrder findDishOrderById(Long id);
}
