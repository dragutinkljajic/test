package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DrinksMenu;
import project.repository.DrinksMenuRepository;

@Service
public class DrinksMenuServiceImpl implements DrinksMenuService{
	
	@Autowired
	private DrinksMenuRepository drinksMenuRepository;
	
	@Override
	public DrinksMenu addDrinksMenu(DrinksMenu drinksMenu) {
		return drinksMenuRepository.save(drinksMenu);
	}

}
