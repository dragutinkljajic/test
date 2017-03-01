package project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.Bid;
import project.domain.Dish;
import project.domain.Drink;
import project.domain.DrinkOffer;
import project.domain.DrinksMenu;
import project.domain.Employee;
import project.domain.EmployeeRole;
import project.domain.Grocery;
import project.domain.GroceryOffer;
import project.domain.Menu;
import project.domain.Offer;
import project.domain.Online;
import project.domain.RestTable;
import project.domain.Restaurant;
import project.domain.RestaurantManager;
import project.domain.SeatingArrangement;
import project.domain.Segment;
import project.domain.Shift;
import project.domain.Supplier;
import project.domain.SystemManager;
import project.domain.User;
import project.domain.WorkSchedule;
import project.domain.dto.BidDTO;
import project.domain.dto.OfferAcceptedDTO;
import project.messaging.OfferMessenger;
import project.repository.ShiftRepository;
import project.service.BidService;
import project.service.DishService;
import project.service.DrinkOfferService;
import project.service.DrinkService;
import project.service.EmployeeService;
import project.service.GroceryOfferService;
import project.service.GroceryService;
import project.service.OfferService;
import project.service.OnlineService;
import project.service.RestaurantService;
import project.service.SegmentService;
import project.service.ShiftService;
import project.service.TableService;
import project.service.UserService;
import project.service.WorkScheduleService;
import project.service.RestManService;

@RequestMapping("/restmanager")
@Controller
public class RestManController {
	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private RestManService restManService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private GroceryService groceryService;
	
	@Autowired
	private BidService bidService;
	
	@Autowired
	private SegmentService segmentService;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private DrinkOfferService drinkOfferService;
	
	@Autowired
	private GroceryOfferService groceryOfferService;
	
	@Autowired
	private OfferMessenger offerMessenger;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ShiftService shiftService;
	
	@RequestMapping(value = "/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<RestaurantManager> loadRestManager(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			RestaurantManager restmanager = restManService.getRestaurantManagerById(online.getUser().getUserID());
			restmanager.setPassword("");
			return new ResponseEntity<RestaurantManager>(restmanager, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value="/getRestaurantNew",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public Restaurant getRestaurantNew(@Context HttpServletRequest request){
		
		Online o = (Online)request.getSession().getAttribute("user");
	
		User u = o.getUser();
		
		RestaurantManager rm = restManService.getRestaurantManagerById(u.getUserID());
		
		return rm.getRestaurant();
	}
	
	@Transactional
	@RequestMapping(value = "updateRestaurantName",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updateRestaurantName(@Context HttpServletRequest request, @RequestBody Restaurant restaurant) {
		
		if (restaurant != null && !restaurant.getName().equals("")) {
			if(restaurantService.getRestaurantByName(restaurant.getName()) == null){
				restaurantService.updateRestaurantName(restaurant);
				return new ResponseEntity<String>(restaurant.getName(), HttpStatus.OK);
			}else{
				if(restaurantService.getRestaurantByName(restaurant.getName()).getRestaurantID() == restaurant.getRestaurantID()){
					return new ResponseEntity<String>("same", HttpStatus.OK);
				}
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("No name sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updateRestaurantDetails",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Restaurant> updateRestaurantDetails(@Context HttpServletRequest request, @RequestBody Restaurant restaurant) {
		
		if (restaurant != null) {
			restaurantService.updateRestaurantDetails(restaurant);
			return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
		}
		
		return new ResponseEntity<Restaurant>(new Restaurant(), HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="/addDish",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addDish(@RequestBody Dish dish){
		
		Dish d = dishService.getDishByLabel(dish.getLabel());
		
		if(d == null){

			dishService.addDish(dish);
			
			return "OK";
		}
		
		return "Dish with that label already exists!";
	}
	
	@Transactional
	@RequestMapping(value="/removeDish",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeDish(@RequestBody Dish dish){
		
		Dish d = dishService.getDishByLabel(dish.getLabel());
		
		if(d != null){

			dishService.deleteDishById(d.getIdDish());
			
			return "Dish removed.";
		}
		
		return "Dish not found!";
	}
	
	@RequestMapping(value="/getDishes",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Dish> getDishes(@Context HttpServletRequest request, @RequestBody Menu menu){
		return this.dishService.getDishesByMenuId(menu.getIdMenu());
	}
	
	@RequestMapping(value="/getDishesNew",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Dish> getDishesNew(@Context HttpServletRequest request){
		
		Online o = (Online)request.getSession().getAttribute("user");
	
		User u = o.getUser();
		
		RestaurantManager rm = restManService.getRestaurantManagerById(u.getUserID());
		
		return this.dishService.getDishesByMenuId(rm.getRestaurant().getMenu().getIdMenu());
	}
	
	@Transactional
	@RequestMapping(value="/addDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addDrink(@RequestBody Drink drink){
		
		Drink d = drinkService.getDrinkByLabel(drink.getLabel());
		
		if(d == null){

			drinkService.addDrink(drink);
			
			return "OK";
		}
		
		return "Drink with that label already exists!";
	}
	
	@Transactional
	@RequestMapping(value="/removeDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeDrink(@RequestBody Drink drink){
		
		Drink d = drinkService.getDrinkByLabel(drink.getLabel());
		
		if(d != null){

			drinkService.deleteDrinkById(d.getIdDrink());
			
			return "Drink removed.";
		}
		
		return "Drink not found!";
	}
	
	@RequestMapping(value="/getDrinks",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Drink> getDrinks(@Context HttpServletRequest request, @RequestBody DrinksMenu drinksMenu){
		return this.drinkService.getDrinksByDrinksMenuId(drinksMenu.getIdDrinkMenu());
	}
	
	@RequestMapping(value="/getAllDrinks",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Drink> getAllDrinks(@Context HttpServletRequest request){
		return this.drinkService.getAllDrinks();
	}
	
	@RequestMapping(value="/getAllGroceries",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Grocery> getAllGroceries(@Context HttpServletRequest request){
		return this.groceryService.getAllGroceries();
	}
	
	@RequestMapping(value="/addBid",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addBid(@Context HttpServletRequest request, @RequestBody Bid bid) throws ParseException{
		bid.setHasOffer(false);
		bidService.addBid(bid);
		
		return "OK";
	}
	
	@Transactional
	@RequestMapping(value="/addSegment",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addSegment(@RequestBody Segment segment){
		
		Segment s = segmentService.getSegmentByLabel(segment.getLabel());
		
		if(s == null){

			segmentService.addSegment(segment);
			
			return "OK";
		}
		
		return "Segment with that label already exists!";
	}
	
	@Transactional
	@RequestMapping(value="/removeSegment",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeSegment(@RequestBody Segment segment){
		
		Segment s = segmentService.getSegmentByLabel(segment.getLabel());
		
		if(s != null){
			
			segmentService.deleteSegmentById(s.getIdSegment());
			
			return "Segment removed.";
		}
		
		return "Segment not found!";
	}
	
	@RequestMapping(value="/getSegments",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Segment> getSegments(@Context HttpServletRequest request, @RequestBody SeatingArrangement seatingArrangement){
		return this.segmentService.getSegmentsBySeatingId(seatingArrangement.getIdSeating());
	}
	
	@Transactional
	@RequestMapping(value="/addTable",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addTable(@RequestBody RestTable restTable){
		
		RestTable table = tableService.getTableByCode(restTable.getTableCode());
		
		
		if(table == null){
			Segment s = segmentService.getSegmentByLabel(restTable.getSegment().getLabel());
			
			restTable.setSegment(s);
			
			tableService.addTable(restTable);
		
			return "Table successfully added!";
		}
		
		return "Unexpected error";
	}
	
	@Transactional
	@RequestMapping(value="/removeTable",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeTable(@RequestBody RestTable restTable){
		
		tableService.deleteTableByCode(restTable.getTableCode());
		
		return "Table successfully removed!";
	}
	
	@RequestMapping(value="/getTables",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RestTable> getTables(@Context HttpServletRequest request, @RequestBody Segment segment){
		return tableService.getTablesBySegmentId(segment.getIdSegment());
	}
	
	@RequestMapping(value="/getOffers",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Offer> getOffers(@Context HttpServletRequest request, @RequestBody Restaurant restaurant){
		return offerService.getOffersByManagerId(restaurant.getRestaurantID());
	}
	
	@org.springframework.transaction.annotation.Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value="/acceptOffer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String acceptOffer(@Context HttpServletRequest request, @RequestBody Offer offer){
		System.out.println(offer.getIdOffer());
		
		System.out.println(offer.getBid().idBid);
		
		Bid realBid = bidService.getBid(offer.getBid().idBid);
		
		if(realBid.hasOffer){
			return "notOK";
		}
		
		List<Offer> realOffers = offerService.getOffersByBidId(realBid.getIdBid());
		
		
		for(Offer o : realOffers){
			Offer realOffer = offerService.getOfferById(o.getIdOffer());
			OfferAcceptedDTO dto = new OfferAcceptedDTO();
			dto.setManagerName(realOffer.getBid().getManager().getName());
			dto.setManagerSurname(realOffer.getBid().getManager().getSurname());
			dto.setRestaurantName(realOffer.getBid().getManager().getRestaurant().getName());
			dto.setReceiverID(realOffer.getSupplier().getUserID());
			dto.setBidId(realBid.idBid);
			dto.setOfferId(o.getIdOffer());
			
			if(offer.getIdOffer() == o.getIdOffer()){
				dto.setAccepted(true);
				o.setStatus("Accepted");
			}else{
				dto.setAccepted(false);
				o.setStatus("Refused");
			}
			
			offerMessenger.sendOfferAcceptedTo(dto);
			
			//drinkOfferService.removeDrinkOfferByOfferId(o.getIdOffer());
			
			//groceryOfferService.removeGroceryOfferByOfferId(o.getIdOffer());
			
			//offerService.deleteOfferById(o.getIdOffer());
		}
		
		realBid.setHasOffer(true);
		//bidService.deleteBidById(realBid.getIdBid());
		
		return "OK";
	}
	
	@Transactional
	@RequestMapping(value="/addEmployee",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addEmployee(@RequestBody Employee employee){
		User u = userService.getUser(employee.getEmail());
		
		if(employee.getRestaurant() != null){
			Restaurant r = this.restaurantService.getRestaurantByName(employee.getRestaurant().getName());
			if(r != null)
				employee.setRestaurant(r);
			else
				return "No such restaurant.";
		}
		
		if(u == null){

			employee.setUserType(project.domain.UserType.EMPLOYEE);
			//restManService.addRestaurantManager(rm);
			employeeService.addEmployee(employee);
			
			return "OK";
		}
		
		return "User email already exists!";
	}
	
	@RequestMapping(value="/getEmployees",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Employee> getEmployees(@Context HttpServletRequest request, @RequestBody Restaurant restaurant){
		return employeeService.getEmployeesByRestaurantId(restaurant.getRestaurantID());
	}
	
	@RequestMapping(value="/getEmployeesNew",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Employee> getEmployeesNew(@Context HttpServletRequest request){
		
		Online o = (Online)request.getSession().getAttribute("user");
	
		User u = o.getUser();
		
		RestaurantManager rm = restManService.getRestaurantManagerById(u.getUserID());
		
		return employeeService.getEmployeesByRestaurantId(rm.getRestaurant().getRestaurantID());
	}
	
	@Transactional
	@RequestMapping(value="/addShift",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public Shift addShift(@RequestBody Shift shift){
		
		Employee e = employeeService.getEmployeeById(shift.getEmployee().getUserID());
		
		Segment s = null;

		if(e.getRole() == EmployeeRole.WAITER){
			s = segmentService.getSegmentByLabel(shift.getSegment().getLabel());
		}
		shift.setEmployee(e);
		shift.setSegment(s);
		
		Shift ret = shiftService.addShift(shift);
		
		return ret;
	}
	
	@Transactional
	@RequestMapping(value="/removeShift",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeShift(@RequestBody Shift shift){
		Shift s = shiftService.getShiftById(shift.getIdShift());
		
		if(s != null){
			shiftService.deleteShiftById(shift.getIdShift());
			
			return "Shift removed.";
		}
		
		return "Shift not found!";
	}
	
	@RequestMapping(value="/getShifts",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Shift> getShifts(@Context HttpServletRequest request, @RequestBody WorkSchedule schedule){
		return shiftService.getShiftsByWorkScheduleId(schedule.getIdSchedule());
	}
}
