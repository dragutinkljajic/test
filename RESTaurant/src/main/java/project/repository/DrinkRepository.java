package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Drink;

public interface DrinkRepository extends Repository<Drink, Long>{
	public Drink save(Drink drink);
	
	@Query("select d from Drink d where d.drinksMenu.idDrinkMenu = ?1")
	public List<Drink> findDrinksByDrinksMenuId(Long id);
	
	@Query("select d from Drink d")
	public List<Drink> findAllDrinks();
	
	@Query("select d from Drink d where d.label =?1")
	public Drink findDrinkByLabel(String label);
	
	@Modifying
	@Query("delete from Drink d where d.idDrink = ?1")
	public void removeDrinkById(Long id);
	
	@Query("select d from Drink d where d.idDrink = ?1")
	public Drink findDrinkById(Long id);
}
