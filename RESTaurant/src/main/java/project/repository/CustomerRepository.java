package project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Customer;

public interface CustomerRepository extends Repository<Customer, Long> {
	
	@Query("select c from Customer c where c.userID = ?1")
	public Customer findCustomerById(Long id);
	
	@Query("select c from Customer c")
	public List<Customer> findAll();
	
	@Modifying
	@Query("delete from Customer c where c.userID = ?1")
	public void removeCustomerById(Long id);
	
	public Customer save(Customer customer);
	
	@Modifying
	@Query("update Customer c set c.email = ?2 where c.userID = ?1 ")
	public void updateEmail(Long id, String email);
	
	@Modifying
	@Query("update Customer c set c.password = ?2 where c.userID = ?1 ")
	public void updatePassword(Long id, String password);
	
	@Modifying
	@Query("update Customer c set c.name = ?2, c.surname = ?3, c.address = ?4, c.dateBirth = ?5 where c.userID = ?1")
	public void updateDetails(Long id, String name, String surname, String address, Date dateBirth);
}
