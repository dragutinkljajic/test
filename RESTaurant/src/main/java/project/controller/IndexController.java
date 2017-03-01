package project.controller;

import java.util.ArrayList;
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

import project.domain.Customer;
import project.domain.Online;
import project.domain.Request;
import project.domain.dto.PasswordDTO;
import project.domain.dto.RequestDTO;
import project.messaging.RequestMessenger;
import project.service.CustomerService;
import project.service.RequestService;

@RequestMapping("/index")
@Controller
public class IndexController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RequestMessenger requestMessenger;
	
	@Autowired
	private RequestService requestService;
	
	@RequestMapping(value = "/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Customer> loadCustomer(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Customer customer = customerService.getCustomerById(online.getUser().getUserID());
			customer.setPassword("");
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return null;
		}
		
	}
	
	@Transactional
	@RequestMapping(value = "updateEmail",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updateEmail(@Context HttpServletRequest request, @RequestBody Customer customer) {
		
		if (customer != null && !customer.getEmail().equals("")) {
			customerService.updateCustomerEmail(customer);
			return new ResponseEntity<String>(customer.getEmail(), HttpStatus.OK);
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
			Customer tmp = customerService.getCustomerById(dto.getUserID());
			if (tmp != null && tmp.getPassword().equals(dto.getCurrentPass())) {
				customerService.updateCustomerPassword(dto.generateCustomer());
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
	public ResponseEntity<Customer> updateDetails(@Context HttpServletRequest request, @RequestBody Customer customer) {
		
		if (customer != null) {
			customerService.updateCustomerDetails(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
		
		return new ResponseEntity<Customer>(new Customer(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "loadFriends",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Customer>> loadFriends(@Context HttpServletRequest request, @RequestBody PasswordDTO data) {
		
		if (data.getUserID() != null) {
			Customer customer = customerService.getCustomerById(data.getUserID());
			if (customer != null) {
				List<Customer> friends = customer.getFriends();
				List<Customer> friendOf = customer.getFriendOf();
				friends.addAll(friendOf);
				for (Customer c : friends) {
					c.setPassword("");
				}
				return new ResponseEntity<List<Customer>>(friends, HttpStatus.OK);
			}
		}
		
		return null;
	}
	
	@RequestMapping(value = "/loadCustomers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Customer>> loadCustomers(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Customer customer = customerService.getCustomerById(online.getUser().getUserID());
			List<Customer> customers = customerService.getAll();
			List<Customer> retVal = new ArrayList<Customer>();
			List<Customer> requested = new ArrayList<Customer>();
			for (Request r : customer.getOutcomingRequests()) {
				if (r.getStatus().equals("Pending"))
					requested.add(r.getReceiver());
			}
			
			for (Customer c : customers) {
				if (!customer.getFriends().contains(c) && !customer.getFriendOf().contains(c) && !requested.contains(c)) {
					c.setPassword("");
					retVal.add(c);
				}
			}
			retVal.remove(customer);
			return new ResponseEntity<List<Customer>>(retVal, HttpStatus.OK);
		} else {
			return null;
		}

	}
	
	@RequestMapping(value = "/loadRequests",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<List<RequestDTO>>> loadRequests(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Customer customer = customerService.getCustomerById(online.getUser().getUserID());
			List<List<RequestDTO>> retVal = new ArrayList<List<RequestDTO>>();
			List<RequestDTO> inc = new ArrayList<RequestDTO>();
			List<RequestDTO> out = new ArrayList<RequestDTO>();
			for (Request r : customer.getIncomingRequests()) {
				inc.add(new RequestDTO(r));
			}
			for (Request r : customer.getOutcomingRequests()) {
				out.add(new RequestDTO(r));
			}
			retVal.add(inc);
			retVal.add(out);
			return new ResponseEntity<List<List<RequestDTO>>>(retVal, HttpStatus.OK);
		} else {
			return null;
		}

	}
	
	
	@RequestMapping(value = "/sendRequest",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> sendRequest(@Context HttpServletRequest request, @RequestBody RequestDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Customer sender = customerService.getCustomerById(dto.getSenderID());
			Customer receiver = customerService.getCustomerById(dto.getReceiverID());
			
			Request req = new Request();
			req.setSender(sender);
			req.setReceiver(receiver);
			req.setStatus("Pending");
			
			List<Request> outcoming = requestService.getAllByCombination(sender, receiver);
			if (outcoming.size() != 0) {
				for (Request r : outcoming) {
					if (r.getStatus().equals("Pending")) {
						return new ResponseEntity<String>("Repeated", HttpStatus.OK);
					}
				}
			}
			
			List<Request> incoming = requestService.getAllByCombination(receiver, sender);
			if (incoming.size() != 0) {
				for (Request r : incoming) {
					if (r.getStatus().equals("Pending")) {
						return new ResponseEntity<String>("Inbox", HttpStatus.OK);
					}
				}
			}
			
			requestService.save(req);
			
			RequestDTO tmp = new RequestDTO(req);
			requestMessenger.sendRequestTo(tmp);
			requestMessenger.sendUpdateTo(tmp);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/acceptRequest",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> acceptRequest(@Context HttpServletRequest request, @RequestBody RequestDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Customer sender = customerService.getCustomerById(dto.getSenderID());
			Customer receiver = customerService.getCustomerById(dto.getReceiverID());
			
			dto.setStatus("Accepted");
			
			if (receiver.getFriendOf().contains(sender))
				return new ResponseEntity<String>("Already accepted", HttpStatus.OK);
			
			Request req = requestService.getRequest(dto.getRequestID());
			req.setStatus("Accepted");
			requestService.save(req);
			
			receiver.getFriendOf().add(sender);
			customerService.save(receiver);
			
			requestMessenger.sendRequestTo(dto);
			requestMessenger.sendUpdateTo(dto);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/declineRequest",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> declineRequest(@Context HttpServletRequest request, @RequestBody RequestDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Customer sender = customerService.getCustomerById(dto.getSenderID());
			Customer receiver = customerService.getCustomerById(dto.getReceiverID());
			
			dto.setStatus("Declined");
			
			Request req = requestService.getRequest(dto.getRequestID());
			if (req.getStatus().equals("Declined"))
				return new ResponseEntity<String>("Already declined", HttpStatus.OK);
			
			req.setStatus("Declined");
			requestService.save(req);
			
			requestMessenger.sendRequestTo(dto);
			requestMessenger.sendUpdateTo(dto);
			
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	
}
