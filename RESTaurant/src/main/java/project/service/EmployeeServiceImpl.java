package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Employee;
import project.domain.EmployeeRole;
import project.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	public List<Employee> getAllByRole(EmployeeRole role){
		return employeeRepository.getAllByRole(role);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Assert.notNull(id, "ID cannot be null");
		return employeeRepository.findEmployeeById(id);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee addEmployee(Employee employee) {
		Assert.notNull(employee, "Cannot be null");
		return employeeRepository.save(employee);
	}

	@Override
	public void updateEmployeeEmail(Employee employee) {
		Assert.notNull(employee, "Cannot be null");
		employeeRepository.updateEmail(employee.getUserID(), employee.getEmail());
	}

	@Override
	public void updateEmployeePassword(Employee employee) {
		Assert.notNull(employee, "Cannot be null");
		employeeRepository.updatePassword(employee.getUserID(), employee.getPassword(), employee.isPassChanged());
		
	}
	
	@Override
	public void updateEmployeeDetails(Employee employee) {
		Assert.notNull(employee, "Cannot be null");
		employeeRepository.updateDetails(employee.getUserID(), employee.getName(), employee.getSurname(), employee.getDateBirth(), employee.getSizeCloth(), employee.getSizeShoes());
	}

	@Override
	public List<Employee> getEmployeesByRestaurantId(Long id) {
		return employeeRepository.findEmployeesByRestaurantId(id);
	}

	

	

}
