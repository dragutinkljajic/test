package project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import project.domain.Customer;
import project.domain.Request;

public interface RequestService {

	List<Request> getAll();
	
	Request getRequest(Long id);
	
	List<Request> getAllBySender(Customer cst);
	
	List<Request> getAllByReceiver(Customer cst);
	
	Request save(Request request);
	
	List<Request> getAllByCombination(Customer sender, Customer receiver);
}
