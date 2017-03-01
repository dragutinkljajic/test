package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.RestOrder;
import project.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Override
	public RestOrder save(RestOrder order) {
		return repository.save(order);
	}

	@Override
	public RestOrder getById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<RestOrder> getAll() {
		return repository.findAll();
	}

}
