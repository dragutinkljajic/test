package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DishOrder;
import project.repository.DishOrderRepository;

@Service
public class DishOrderServiceImpl implements DishOrderService {

	@Autowired
	private DishOrderRepository repository;
	
	@Override
	public DishOrder save(DishOrder dishOrder) {
		return repository.save(dishOrder);
	}

	@Override
	public DishOrder getDishById(Long id) {
		return repository.findDishOrderById(id);
	}

	@Override
	public List<DishOrder> getAll() {
		return repository.findAll();
	}



}
