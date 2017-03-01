package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Grocery;

public interface GroceryRepository extends Repository<Grocery, Long>{
	public Grocery save(Grocery grocery);
	
	@Query("select g from Grocery g where g.label =?1")
	public Grocery findGroceryByLabel(String label);
	
	@Modifying
	@Query("delete from Grocery g where g.idGrocery = ?1")
	public void removeGroceryById(Long id);

	@Query("select g from Grocery g")
	public List<Grocery> findAllGroceries();
}
