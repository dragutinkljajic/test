package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Customer;
import project.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Assert.notNull(id, "ID cannot be null");
		return customerRepository.findCustomerById(id);
	}

	@Override
	public void deleteCustomerById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		return customerRepository.save(customer);
	}

	@Override
	public void updateCustomerEmail(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		customerRepository.updateEmail(customer.getUserID(), customer.getEmail());
	}

	@Override
	public void updateCustomerPassword(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		customerRepository.updatePassword(customer.getUserID(), customer.getPassword());
		
	}
	
	@Override
	public void updateCustomerDetails(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		customerRepository.updateDetails(customer.getUserID(), customer.getName(), customer.getSurname(), customer.getAddress(), customer.getDateBirth());
	}

	@Override
	public Customer save(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		return customerRepository.save(customer);
	}

	

	

}
