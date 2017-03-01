package project.controller;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.DrinksMenu;
import project.domain.Menu;
import project.domain.Restaurant;
import project.domain.RestaurantManager;
import project.domain.SeatingArrangement;
import project.domain.SystemManager;
import project.domain.User;
import project.domain.WorkSchedule;
import project.service.DrinksMenuService;
import project.service.MenuService;
import project.service.RestManService;
import project.service.RestaurantService;
import project.service.SeatingArrangementService;
import project.service.SysManService;
import project.service.UserService;
import project.service.WorkScheduleService;
@RequestMapping("/sysman")
@Controller
public class SysManController {
	@Autowired
	private SysManService sysManService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestManService restManService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private DrinksMenuService drinksMenuService;
	
	@Autowired
	private SeatingArrangementService seatingArrangementService;
	
	@Autowired
	private WorkScheduleService workScheduleService;
	
	@Transactional
	@RequestMapping(value="/addSystemManager",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addSystemManager(@RequestBody SystemManager sm){
		
		User u = userService.getUser(sm.getEmail());
		
		if(u == null){

			sm.setUserType(project.domain.UserType.SYSMANAGER);
			sysManService.addSystemManager(sm);
			
			return "OK";
		}
		
		return "User email already exists!";
	}
	
	@Transactional
	@RequestMapping(value="/addRestaurantManager",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addRestaurantManager(@RequestBody RestaurantManager rm){
		User u = userService.getUser(rm.getEmail());
		
		if(rm.getRestaurant() != null){
			Restaurant r = this.restaurantService.getRestaurantByName(rm.getRestaurant().getName());
			if(r != null)
				rm.setRestaurant(r);
			else
				return "No such restaurant.";
		}
		
		if(u == null){

			rm.setUserType(project.domain.UserType.RESTMANAGER);
			restManService.addRestaurantManager(rm);
			
			return "OK";
		}
		
		return "User email already exists!";
	}
	
	@RequestMapping(value="/getSystemManagers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<SystemManager> getSystemManagers(){
		return sysManService.getAll();
	}
	
	@RequestMapping(value="/getRestaurantManagers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RestaurantManager> getRestaurantManagers(){
		
		return restManService.getAll();
	}
	
	@RequestMapping(value="/getRestaurants",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Restaurant> getRestaurants(){
		return restaurantService.getAll();
	}
	
	@RequestMapping(value="/getRestaurantByName",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public Restaurant getRestaurantByName(@RequestBody Restaurant r){
		return this.restaurantService.getRestaurantByName(r.getName());
	}
	
	@Transactional
	@RequestMapping(value="/addRestaurant",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addRestaurant(@RequestBody Restaurant r){
		
		
		Restaurant restaurant = restaurantService.getRestaurantByName(r.getName());
		
		if(restaurant == null){
			
			restaurantService.addRestaurant(r);
			
			Menu menu = new Menu();
			menu.setRestaurant(r);
			DrinksMenu drinksMenu = new DrinksMenu();
			drinksMenu.setRestaurant(r);
			SeatingArrangement seatingArrangement = new SeatingArrangement();
			seatingArrangement.setRestaurant(r);
			WorkSchedule workSchedule = new WorkSchedule();
			workSchedule.setRestaurant(r);
			
			menuService.addMenu(menu);
			drinksMenuService.addDrinksMenu(drinksMenu);
			seatingArrangementService.addSeatingArrangement(seatingArrangement);
			workScheduleService.addWorkSchedule(workSchedule);
			
			return "OK";
		}
		
		return "Restaurant name already exists!";
	}
}
