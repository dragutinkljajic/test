package project.repository;

import org.springframework.data.repository.Repository;

import project.domain.DrinksMenu;

public interface DrinksMenuRepository extends Repository<DrinksMenu, Long>{
	public DrinksMenu save(DrinksMenu drinksMenu);
}
