package project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Employee;
import project.domain.EmployeeRole;

public interface EmployeeRepository extends Repository<Employee, Long> {
	
	@Query("select e from Employee e where e.userID = ?1")
	public Employee findEmployeeById(Long id);
	
	@Query("select e from Employee e")
	public List<Employee> findAll();
	
	@Query("select e from Employee e where e.role = ?1")
	public List<Employee> getAllByRole(EmployeeRole role);
	
	@Query("select e from Employee e where e.restaurant.restaurantID = ?1")
	public List<Employee> findEmployeesByRestaurantId(Long id);
	
	@Modifying
	@Query("delete from Employee e where e.userID = ?1")
	public void removeEmployeeById(Long id);
	
	public Employee save(Employee customer);
	
	@Modifying
	@Query("update Employee e set e.email = ?2 where e.userID = ?1 ")
	public void updateEmail(Long id, String email);
	
	@Modifying
	@Query("update Employee e set e.password = ?2, e.passChanged = ?3 where e.userID = ?1 ")
	public void updatePassword(Long id, String password, boolean change);
	
	@Modifying
	@Query("update Employee e set e.name = ?2, e.surname = ?3, e.sizeCloth = ?5, e.sizeShoes = ?6, e.dateBirth = ?4 where e.userID = ?1")
	public void updateDetails(Long id, String name, String surname, Date dateBirth, int sizeCloth, int sizeShoes);
}
