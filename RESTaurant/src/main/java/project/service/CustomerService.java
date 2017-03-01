package project.service;

import java.util.List;

import project.domain.Customer;

public interface CustomerService {

	List<Customer> getAll();
	
	Customer getCustomerById(Long id);
	
	void deleteCustomerById(Long id);
	
	Customer addCustomer(Customer customer);
	
	void updateCustomerEmail(Customer customer);
	
	void updateCustomerPassword(Customer customer);
	
	void updateCustomerDetails(Customer customer);
	
	Customer save(Customer customer);
}
