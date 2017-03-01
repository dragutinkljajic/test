package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Dish;

public interface DishRepository extends Repository<Dish, Long>{
	public Dish save(Dish dish);
	
	@Query("select d from Dish d where d.menu.idMenu = ?1")
	public List<Dish> findDishesByMenuId(Long id);
	
	@Query("select d from Dish d where d.label =?1")
	public Dish findDishByLabel(String label);
	
	@Modifying
	@Query("delete from Dish d where d.idDish = ?1")
	public void removeDishById(Long id);
	
	@Query("select d from Dish d where d.idDish = ?1")
	public Dish findDishById(Long id);
}
