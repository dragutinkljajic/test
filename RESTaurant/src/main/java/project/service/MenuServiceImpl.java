package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Menu;
import project.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public Menu addMenu(Menu menu) {
		return this.menuRepository.save(menu);
	}

}
