package project.service;

import java.util.List;

import project.domain.RestOrder;

public interface OrderService {

	public RestOrder save(RestOrder order);
	
	public  RestOrder getById(Long id);
	
	public List<RestOrder> getAll();
}
