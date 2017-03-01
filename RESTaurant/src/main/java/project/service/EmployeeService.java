package project.service;

import java.util.List;

import project.domain.Employee;
import project.domain.EmployeeRole;

public interface EmployeeService {

	List<Employee> getAll();
	
	List<Employee> getAllByRole(EmployeeRole role);
	
	List<Employee> getEmployeesByRestaurantId(Long id);
	
	Employee getEmployeeById(Long id);
	
	void deleteEmployeeById(Long id);
	
	Employee addEmployee(Employee employee);
	
	void updateEmployeeEmail(Employee employee);
	
	void updateEmployeePassword(Employee employee);
	
	void updateEmployeeDetails(Employee employee);
}
