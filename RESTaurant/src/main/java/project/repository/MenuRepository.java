package project.repository;

import org.springframework.data.repository.Repository;

import project.domain.Menu;

public interface MenuRepository extends Repository<Menu, Long>{
	public Menu save(Menu menu);
}
