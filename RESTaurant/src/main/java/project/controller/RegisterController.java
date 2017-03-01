package project.controller;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.Customer;
import project.domain.Supplier;
import project.domain.Token;
import project.domain.UserType;
import project.service.CustomerService;
import project.service.SupplierService;
import project.service.TokenService;
import project.service.UserService;

@RequestMapping("/register")
@Controller
public class RegisterController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@RequestMapping(value = "/customer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> registerCustomer(@Context HttpServletRequest request, @RequestBody Customer cst) {
		
		String hash = UUID.randomUUID().toString();
		
		if (userService.getUser(cst.getEmail()) != null) {
			return new ResponseEntity<String>("Provided e-mail already in use.", HttpStatus.OK);
		}
		
		Token tmpToken = tokenService.getTokenByEmail(cst.getEmail());
		Token token = new Token(hash, cst.getEmail(), cst.getPassword(), cst.getName(), cst.getSurname());
		if (tmpToken != null) {
			token.setId(tmpToken.getId());
		}
		token.setExpiryDate(token.calculateExpiryDate());
		tokenService.addToken(token);
		System.out.println(hash);
		
		String appLocation = getURL(request);
		String url = appLocation + "/register/confirm?token=" + hash;
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(cst.getEmail());
        message.setSubject("Registration confirmation");
        message.setText("Please follow the link below to confirm your registration. \n\n" + url);
        javaMailSender.send(message);
        
		return new ResponseEntity<String>("A confirmation link has been sent to provided e-mail address.", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/supplier",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> registerSupplier(@Context HttpServletRequest request, @RequestBody Supplier s) {
		
		String hash = UUID.randomUUID().toString();
		
		if (userService.getUser(s.getEmail()) != null) {
			return new ResponseEntity<String>("Provided e-mail already in use.", HttpStatus.OK);
		}
		
		Token tmpToken = tokenService.getTokenByEmail(s.getEmail());
		Token token = new Token(hash, s.getEmail(), s.getPassword(), s.getLabel(), s.getDescription());
		if (tmpToken != null) {
			token.setId(tmpToken.getId());
		}
		token.setExpiryDate(token.calculateExpiryDate());
		tokenService.addToken(token);
		System.out.println(hash);
		
		String appLocation = getURL(request);
		String url = appLocation + "/register/confirmsup?token=" + hash;
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(s.getEmail());
        message.setSubject("Registration confirmation");
        message.setText("Please follow the link below to confirm your registration. \n\n" + url);
        javaMailSender.send(message);
        
		return new ResponseEntity<String>("A confirmation link has been sent to provided e-mail address.", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm",
			method = RequestMethod.GET)
	public String confirmRegistration(@Context HttpServletRequest request, @RequestParam("token") String token) {
		
		Token tok = tokenService.getTokenByHash(token);
		
		if (tok == null) {
			return "redirect:/register.html";
		}
		
		Calendar cal = Calendar.getInstance();
		if ((tok.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "redirect:/register.html";
		}
		
		Customer cst = new Customer();
		cst.setEmail(tok.getEmail());
		cst.setPassword(tok.getPassword());
		cst.setUserType(UserType.CUSTOMER);
		if (tok.getName() != "")
			cst.setName(tok.getName());
		if (tok.getSurname() != "")
			cst.setSurname(tok.getSurname());
		
		customerService.addCustomer(cst);
		
		return "redirect:/login.html";
		
		
	}
	
	@RequestMapping(value = "/confirmsup",
			method = RequestMethod.GET)
	public String confirmRegistrationSupplier(@Context HttpServletRequest request, @RequestParam("token") String token) {
		
		Token tok = tokenService.getTokenByHash(token);
		
		if (tok == null) {
			return "redirect:/register.html";
		}
		
		Calendar cal = Calendar.getInstance();
		if ((tok.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "redirect:/register.html";
		}
		
		Supplier s = new Supplier();
		s.setEmail(tok.getEmail());
		s.setPassword(tok.getPassword());
		s.setUserType(UserType.SUPPLIER);
		if (tok.getName() != "")
			s.setLabel(tok.getName());
		if (tok.getSurname() != "")
			s.setDescription(tok.getSurname());
		
		supplierService.addSupplier(s);
		
		return "redirect:/login.html";
		
		
	}
	
	public static String getURL(HttpServletRequest req) {

	    String scheme = req.getScheme();
	    String serverName = req.getServerName();
	    int serverPort = req.getServerPort();        
	    String contextPath = req.getContextPath();    

	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != 80 && serverPort != 443) {
	        url.append(":").append(serverPort);
	    }

	    url.append(contextPath);

	    return url.toString();
	}
}
