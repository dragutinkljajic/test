package project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.domain.Employee;
import project.domain.EmployeeRole;
import project.domain.Online;
import project.domain.dto.PasswordDTO;
import project.service.EmployeeService;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Employee> loadEmployee(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Employee employee = employeeService.getEmployeeById(online.getUser().getUserID());
			employee.setPassword("");
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} else {
			return null;
		}
		
	}
	
	@RequestMapping(value = "getRole",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Employee>> getByRole(@Context HttpServletRequest request, @RequestBody String employee) {
		System.out.println(employee);
		EmployeeRole number;
		if (employee != null) {
			if(employee.equals("Chef")){
				number = EmployeeRole.CHEF;
			} else if (employee.equals("Bartender")){
				number = EmployeeRole.BARTENDER;
			} else {
				number = EmployeeRole.WAITER;
			}
			List<Employee> lista = employeeService.getAll();
			return new ResponseEntity<List<Employee>>(lista, HttpStatus.OK);
		}
		
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "updateEmail",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updateEmail(@Context HttpServletRequest request, @RequestBody Employee employee) {
		
		if (employee != null && !employee.getEmail().equals("")) {
			employeeService.updateEmployeeEmail(employee);
			return new ResponseEntity<String>(employee.getEmail(), HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("No email sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updatePassword",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updatePassword(@Context HttpServletRequest request, @RequestBody PasswordDTO dto) {
		
		if (dto != null && !dto.getCurrentPass().equals("") && !dto.getNewPass().equals("")) {
			Employee tmp = employeeService.getEmployeeById(dto.getUserID());
			if (tmp != null && tmp.getPassword().equals(dto.getCurrentPass())) {
				employeeService.updateEmployeePassword(dto.generateEmployee());
				return new ResponseEntity<String>("Password successfully changed", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Invalid password", HttpStatus.OK);
			}
			
		}
		
		return new ResponseEntity<String>("Invalid data sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updateDetails",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Employee> updateDetails(@Context HttpServletRequest request, @RequestBody Employee employee) {
		
		if (employee != null) {
			employeeService.updateEmployeeDetails(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		
		return new ResponseEntity<Employee>(new Employee(), HttpStatus.OK);
	}
	
	


}
