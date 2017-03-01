package project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.domain.Online;
import project.domain.User;
import project.service.OnlineService;
import project.service.UserService;

@RequestMapping("/login")
@Controller
public class LoginController {

	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping(value="/login",
			method = RequestMethod.POST,
			consumes= MediaType.APPLICATION_JSON,
			produces= MediaType.TEXT_PLAIN)
	public ResponseEntity<String> login(@Context HttpServletRequest request, @RequestBody User usr) {

		if (usr == null) {
			return new ResponseEntity<String>("Nothing sent", HttpStatus.OK);
		}
		
		if (usr.getEmail() == null) {
			return new ResponseEntity<String>("No email", HttpStatus.OK);
		}
		
		if (usr.getPassword() == null) {
			return new ResponseEntity<String>("No password", HttpStatus.OK);
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		Online current = (Online) request.getSession().getAttribute("user");
		
		if (current != null) {
			if (current.getUser().getEmail().equals(usr.getEmail())) {
				return new ResponseEntity<String>("Already logged in", HttpStatus.OK);
			} else {
				onlineService.deleteOnline(current);
				request.getSession().setAttribute("user", null);
			}
		} 
		
		User user = userService.getUser(usr.getEmail());
		
		if (user != null) {
			if (usr.getPassword().equals(user.getPassword())) {
				
				Online onl = new Online();
				onl.setUser(user);
				onl = onlineService.addOnline(onl);
				request.getSession().setAttribute("user", onl);
				
				headers.add("Location", "/index.html");
				
				return new ResponseEntity<String>("Logged in:" + user.getUserType().toString(), headers, HttpStatus.OK);
			} 
		} 
		
		return new ResponseEntity<String>("Invalid credentials", headers, HttpStatus.OK);
	}
	
	
	
	@Transactional
	@RequestMapping(value = "/logout",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> logout(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online == null) {
			return new ResponseEntity<String>("Not logged in", HttpStatus.OK);
		}
		
		onlineService.deleteOnline(online);
		request.getSession().invalidate();
		
		return new ResponseEntity<String>("Logged out", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/authorize",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> authorize(@Context HttpServletRequest request, @RequestBody String type) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (type.equals("index")) {
			if (online == null) {
				
				return new ResponseEntity<String>("Not logged in", HttpStatus.OK);
			}
		}
		
		if (type.equals("login")) {
			if (online != null) {
				return new ResponseEntity<String>("Logged in", HttpStatus.OK);
			}
		}
		
		
		return new ResponseEntity<String>("Authorized", HttpStatus.OK);
	}
}
