package project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.domain.Online;
import project.domain.User;
import project.service.OnlineService;
import project.service.UserService;

@Controller
public class MainController {

	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"/"},
			method = {RequestMethod.GET, RequestMethod.POST})
	public String authorize(@Context HttpServletRequest request) {
		Online onl = (Online) request.getSession().getAttribute("user");
		if (onl == null) {
			
			return "redirect:/login.html";
		} else {
			User user = userService.getUserById(onl.getId());
			String retVal = "";
			switch (user.getUserType()) {
			
				case CUSTOMER:
					retVal = "redirect:/index.html"; 
					break;
				case EMPLOYEE:
					//employee page
					break;
				case RESTMANAGER:
					//restoraunt manager page
					break;
				case SUPPLIER:
					//supplier page
					break;
				case SYSMANAGER:
					retVal = "redirect:/sysmanager.html"; 
					break;
				default:
					break;
			
			}
			
			return retVal;
		}
	}
	
}
