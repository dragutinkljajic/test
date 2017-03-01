package project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.Customer;
import project.domain.Dish;
import project.domain.DishOrder;
import project.domain.Drink;
import project.domain.DrinkOrder;
import project.domain.Online;
import project.domain.Reservation;
import project.domain.RestOrder;
import project.domain.RestTable;
import project.domain.Restaurant;
import project.domain.Segment;
import project.domain.dto.PasswordDTO;
import project.domain.dto.ReservDTO;
import project.service.CustomerService;
import project.service.DishOrderService;
import project.service.DishService;
import project.service.DrinkOrderService;
import project.service.DrinkService;
import project.service.OrderService;
import project.service.ReservationService;
import project.service.RestaurantService;
import project.service.SegmentService;
import project.service.TableService;

@RequestMapping("/reservations")
@Controller
public class ReservationsController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private SegmentService segmentService;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private DishOrderService dishOrderService;
	
	@Autowired
	private DrinkOrderService drinkOrderService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/getRestaurants",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Restaurant>> loadCustomer(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			List<Restaurant> restaurants = restaurantService.getAll();
			return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@Transactional
	@RequestMapping(value = "/clean",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> clean(@Context HttpServletRequest request, @RequestBody PasswordDTO dto) {

		if (dto != null) {
			Customer cst = customerService.getCustomerById(dto.getUserID());
			if (cst != null) {
				reservationService.removeAllByStatusAndUserId(cst.getUserID(), "Setup");
				return new ResponseEntity<String>("Clean", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("No user", HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/term",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<ReservDTO> term(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Customer customer = customerService.getCustomerById(online.getUser().getUserID());
			Restaurant rest = restaurantService.getRestaurantById(dto.getRestID());
			
			Date now = new Date();
			if (checkDate(now, dto.getDate()) == -1) {
				dto.setStatus("Wrong date");
				return new ResponseEntity<ReservDTO>(dto, HttpStatus.OK);
			} else if (checkDate(now, dto.getDate()) == 0) {
				if (checkTime(now, dto.getTime()) == -1) {
					dto.setStatus("Wrong time");
					return new ResponseEntity<ReservDTO>(dto, HttpStatus.OK);
				}
			}						
			
			Reservation res = new Reservation();
			res.setCustomer(customer);
			res.setRestaurant(rest);
			res.setStatus("Setup");
			res.setDate(dto.getDate());
			res.setTime(dto.getTime());
			res.setDuration(dto.getDuration());
			
			reservationService.save(res);
			
			ReservDTO tmp = new ReservDTO(res);
			
			return new ResponseEntity<ReservDTO>(tmp, HttpStatus.OK);
		} 
		
		return null;
	}
	
	@RequestMapping(value = "/getSegments",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Segment>> loadSegments(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Restaurant rest = restaurantService.getRestaurantById(id);
			List<Segment> segments = segmentService.getSegmentsBySeatingId(rest.seatingArrangement.getIdSeating());
			return new ResponseEntity<List<Segment>>(segments, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getDrinks",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Set<Drink>> loadDrinks(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Restaurant rest = restaurantService.getRestaurantById(id);
			Set<Drink> drinks = rest.drinksMenu.getDrinks();
			return new ResponseEntity<Set<Drink>>(drinks, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getDishes",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Set<Dish>> loadDishes(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Restaurant rest = restaurantService.getRestaurantById(id);
			Set<Dish> dishes = rest.menu.dishes;
			return new ResponseEntity<Set<Dish>>(dishes, HttpStatus.OK);
		} else {
			return null;
		}
	}
	/*
	@RequestMapping(value = "/getTables",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<RestTable>> loadTables(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			List<RestTable> tables = tableService.getTablesBySegmentId(id);
			return new ResponseEntity<List<RestTable>>(tables, HttpStatus.OK);
		} else {
			return null;
		}
	}*/
	
	
	@RequestMapping(value = "/getTables",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<RestTable>> loadTables(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			List<RestTable> tables = tableService.getTablesBySegmentId(dto.getSegmentID());
			/*
			Restaurant rest = restaurantService.getRestaurantById(dto.getRestID());
			List<Reservation> tmp = rest.getReservations();
			List<Reservation> reservs = purge(tmp, dto);
			System.out.println(reservs.size());
			*/
			return new ResponseEntity<List<RestTable>>(tables, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/selectTable",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> selectTable(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			Reservation reservation = reservationService.findById(dto.getReservID());
			RestTable table = tableService.getTableByCode(dto.getCurrentTableCode());
			
			//vremenska zauzetost
			if (table != null && reservation != null) {
				if (!table.getStatus().equals("taken")) {
					reservation.getTables().add(table);
					reservationService.save(reservation);
					//table.setStatus("taken");
					//tableService.addTable(table);
					return new ResponseEntity<String>("Success", HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<String>("Null madjija", HttpStatus.OK);
			}
			
			return new ResponseEntity<String>("Unavailable", HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/finish",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> finish(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			//DATI MU GA MALO PO
			Reservation res = reservationService.findById(dto.getReservID());
			res.setStatus("Reserved");
			
			RestOrder order = new RestOrder();
			order.setCustomer(res.getCustomer());
			order.setOnArrival(dto.isOnArrival());
			order.setReservation(res);
			order.setTable(tableService.getTableByCode(dto.getCurrentTableCode()));
			order.setStatus("Fresh");
			
			orderService.save(order);
			
			order.setDishOrders(new ArrayList<DishOrder>());
			order.setDrinkOrders(new ArrayList<DrinkOrder>());
			for (Long dishID : dto.getDishes()) {
				DishOrder dOrder = new DishOrder();
				dOrder.setStatus("Fresh");
				dOrder.setDish(dishService.getDishById(dishID));
				dOrder.setOrder(order);
				
				order.getDishOrders().add(dOrder);
				dishOrderService.save(dOrder);
			}
			for (Long drinkID : dto.getDrinks()) {
				DrinkOrder dOrder = new DrinkOrder();
				dOrder.setStatus("Fresh");
				dOrder.setDrink(drinkService.getDrinkById(drinkID));
				dOrder.setOrder(order);
				order.getDrinkOrders().add(dOrder);
				drinkOrderService.save(dOrder);
			}
			
			orderService.save(order);
			reservationService.save(res);
			
			
			//ODAVDE POSALJI STA HOCES KOME HOCES
			//za referencu pogledaj metodu sendRequest u IndexControlleru
			//u principu samo napravis Messenger klasu kao onu moju, samo sa cime ti hoces kao topicom
			//i ovde pozoves njenu metodu da posaljes poruku, tipa
			//messenger.sendToKonobar(konobar, order.getId());
			//ili sta ti vec odgovara
			//pocupaj konobara iz smena i tih sranja, to nisam gledao kako je implementirano...
			
			
			return new ResponseEntity<String>("Unavailable", HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	private int checkDate(Date now, Date reserv) {
		if (reserv.getYear() > now.getYear()) {
			return 1;
		} else if (reserv.getYear() == now.getYear()) {
			if (reserv.getMonth() > now.getMonth()) {
				return 1;
			} else if (reserv.getMonth() == now.getMonth()) {
				if (reserv.getDate() > now.getDate()) {
					return 1;
				} else if (reserv.getDate() == now.getDate()) {
					return 0;
				}
			}
		}
	
		return -1;
	}
	
	@SuppressWarnings("deprecation")
	private int checkTime(Date now, Date reserv) {
		if (reserv.getHours() > now.getHours()) {
			return 1;
		} else if (reserv.getHours() == now.getHours())
			if (reserv.getMinutes() >= now.getMinutes())
				return 1;
		
		return -1;
	}
	
	private List<Reservation> purge(List<Reservation> in, ReservDTO dto) {
		List<Reservation> out = new ArrayList<Reservation>();
		for (Reservation r : in) {
			if (checkDate(r.getDate(), dto.getDate()) == 0) {
				System.out.println("isti datum");
				
				Calendar calDTO1 = Calendar.getInstance();
				calDTO1.setTime(dto.getTime());
				
				Calendar calDTO2 = Calendar.getInstance();
				calDTO2.setTime(dto.getTime());
				calDTO2.add(Calendar.HOUR_OF_DAY, dto.getDuration());
				
				Calendar calRES1 = Calendar.getInstance();
				calRES1.setTime(r.getTime());
				
				Calendar calRES2 = Calendar.getInstance();
				calRES2.setTime(r.getTime());
				calRES2.add(Calendar.HOUR_OF_DAY, r.getDuration());
				
				if ((calDTO1.before(calRES1) && calDTO2.before(calRES2)) || (calRES1.before(calDTO1) && calRES2.before(calDTO2))) {
					continue;
				}
				
				out.add(r);
			}
		}
		
		
		return out;
	}
}
