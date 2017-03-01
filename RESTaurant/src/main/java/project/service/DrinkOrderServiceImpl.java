package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DrinkOrder;
import project.repository.DrinkOrderRepository;

@Service
public class DrinkOrderServiceImpl implements DrinkOrderService {

	@Autowired
	private DrinkOrderRepository repository;
	
	@Override
	public DrinkOrder save(DrinkOrder drinkOrder) {
		return repository.save(drinkOrder);
	}

	@Override
	public DrinkOrder getDrinkOrderById(Long id) {
		return repository.findDrinkOrderById(id);
	}

	@Override
	public List<DrinkOrder> getAll() {
		return repository.findAll();
	}

}
